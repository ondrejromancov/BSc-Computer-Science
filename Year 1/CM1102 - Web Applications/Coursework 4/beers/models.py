#adapted from https://github.com/guinslym/django-by-example-book
from django.db import models
from django.core.urlresolvers import reverse

class Brand(models.Model):
    name = models.CharField(max_length = 20)
    country = models.CharField(max_length = 30)
    slug = models.SlugField(max_length=30, db_index = True)

    def __str__(self):
        return (self.name)

class Style(models.Model):
    style = models.CharField(max_length = 50)
    slug = models.SlugField(max_length = 50, db_index = True, unique = True)

    class Meta:
        ordering = ('style',)
        verbose_name = 'style'
        verbose_name_plural = 'styles'

    def __str__(self):
        return self.style

    def get_absolute_url(self):
        return reverse('beers:beer_list_by_style', args=[self.slug])

class Beer(models.Model):
    style = models.ForeignKey(Style, related_name='books')
    title = models.CharField(max_length = 100, db_index=True)
    slug = models.SlugField(max_length=100, db_index=True)
    brand = models.ForeignKey(Brand)
    image = models.ImageField(upload_to='books/books_img', blank=True)
    description = models.TextField(blank = True, null = True)
    alcohol_percentage = models.DecimalField(decimal_places=2, max_digits=4)
    price = models.DecimalField(decimal_places=2, max_digits=10)
    stock = models.PositiveIntegerField()
    available = models.BooleanField(default=True)

    class Meta:
        ordering = ('-price',)
        index_together = (('id', 'slug'),)

    def __str__(self):
    	return self.title

    def get_absolute_url(self):
        return reverse('beers:beer_detail', args=[self.id, self.slug])