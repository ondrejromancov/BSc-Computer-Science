#from django.contrib import admin
#from .models import Order, OrderItem


#class OrderItemInline(admin.TabularInline):
#    model = OrderItem
#    raw_id_fields = ['beer']


#class OrderAdmin(admin.ModelAdmin):
#    list_display = ['id', 'first_name', 'last_name', 'email', 'address', 'postcode', 'city', 'paid', 'created', 'updated']
#    list_filter = ['paid', 'created', 'updated']
#    inlines = [OrderItemInline]
    
#admin.site.register(Order, OrderAdmin)