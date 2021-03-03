from django.shortcuts import render, get_object_or_404
from .models import Style, Beer
from cart.forms import CartAddProductForm
from django.http import HttpResponseRedirect


def beer_list(request, style_slug=None):
    style = None
    styles = Style.objects.all()
    beers = Beer.objects.filter(available=True)
    if style_slug:
        style = get_object_or_404(Style, slug=style_slug)
        beers = beers.filter(style=style)
    return render(request, 'beers/beer/list.html', {'style': style,
                                                      'styles': styles,
                                                      'beers': beers})


def beer_detail(request, id, slug):
    beer = get_object_or_404(Beer, id=id, slug=slug, available=True)
    
    cart_beer_form = CartAddProductForm()
    return render(request,
                  'beers/beer/detail.html',
                  {'beer': beer,
                  'cart_beer_form': cart_beer_form})

def about(request):
    return HttpResponseRedirect('beers/beer/about.html')