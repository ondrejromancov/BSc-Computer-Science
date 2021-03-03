#adapted from https://github.com/guinslym/django-by-example-book
from django.contrib import admin
from .models import Beer, Brand, Style

class StyleAdmin(admin.ModelAdmin):
    list_display = ['style', 'slug']
    prepopulated_fields = {'slug': ('style',)}
admin.site.register(Style, StyleAdmin)


class BeerAdmin(admin.ModelAdmin):
    list_display = ['title', 'slug', 'brand', 'style', 'price', 'stock', 'available', 'alcohol_percentage']
    list_filter = ['available', 'alcohol_percentage', 'style']
    list_editable = ['price', 'stock', 'available']
    prepopulated_fields = {'slug': ('title',)}
admin.site.register(Beer, BeerAdmin)

class BrandAdmin(admin.ModelAdmin):
    list_display = ['name', 'country', 'slug']
    prepopulated_fields = {'slug': ('name',)}
admin.site.register(Brand, BrandAdmin)
