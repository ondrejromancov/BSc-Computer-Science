from decimal import Decimal
from django.conf import settings
from beers.models import Beer


class Cart(object):

    def __init__(self, request):

        self.session = request.session
        cart = self.session.get(settings.CART_SESSION_ID)
        if not cart:

            cart = self.session[settings.CART_SESSION_ID] = {}
        self.cart = cart

    def __len__(self):

        return sum(item['quantity'] for item in self.cart.values())

    def __iter__(self):

        beer_ids = self.cart.keys()


        beers = Beer.objects.filter(id__in = beer_ids)
        for beer in beers:
            self.cart[str(beer.id)]['beer'] = beer

        for item in self.cart.values():
            item['price'] = Decimal(item['price'])
            item['total_price'] = item['price'] * item['quantity']
            yield item

    def add(self, beer, quantity=1, update_quantity=False):
        
        beer_id = str(beer.id)
        if beer_id not in self.cart:
            self.cart[beer_id] = {'quantity': 0,
                                      'price': str(beer.price)}
        if update_quantity:
            self.cart[beer_id]['quantity'] = quantity
        else:
            self.cart[beer_id]['quantity'] += quantity
        self.save()

    def remove(self, beer):

        beer_id = str(beer.id)
        if beer_id in self.cart:
            del self.cart[beer_id]
            self.save()

    def save(self):

        self.session[settings.CART_SESSION_ID] = self.cart

        self.session.modified = True

    def clear(self):

        self.session[settings.CART_SESSION_ID] = {}
        self.session.modified = True

    def get_total_price(self):
        return sum(Decimal(item['price']) * item['quantity'] for item in self.cart.values())
