from django.conf.urls import url
from . import views


urlpatterns = [
	url(r'^$', views.beer_list, name='beer_list'),
    url(r'^(?P<style_slug>[-\w]+)/$', views.beer_list, name = 'beer_list_by_style'),
    url(r'^(?P<id>\d+)/(?P<slug>[-\w]+)/$', views.beer_detail, name = 'beer_detail'),
    url(r'^about/$', views.about, name = 'about'),
]