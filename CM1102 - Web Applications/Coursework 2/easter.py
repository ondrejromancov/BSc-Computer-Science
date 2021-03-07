#!/usr/bin/python3
import cgitb
import cgi
cgitb.enable()
form = cgi.FieldStorage()
y = int(form.getvalue("year"))

months = {1: 'January',
		2: 'February',
		3: 'March',
		4: 'April',
		5: 'May',
		6: 'June',
		7: 'July',
		8: 'August',
		9: 'September',
		10: 'October',
		11: 'November',
		12: 'December'
}


def Easter(y):
	a = y % 19
	b = y // 100 
	c = y % 100 
	d = b // 4 
	e = b%4
	g = (8 * b + 13) // 25
	h = (19 * a + b - d - g + 15) % 30
	j = c // 4
	k = c%4
	m = (a + 11 * h) // 319
	r = (2 * e + 2 * j - k - h + m + 32) % 7 
	n = (h - m + r + 90) // 25
	p = (h - m + r + n + 19) % 32
	if form.getvalue("formatstyle") == 'nume':
		return(str(p) + "/" + str(n) + "/" + str(y))
	if form.getvalue("formatstyle") == 'verb':
		if int(p) == 1 or int(p) == 21 or int(p) == 31:
			superscript = 'st'
		elif int(p) == 2 or int(p) == 22:
			superscript = 'nd'
		elif int(p) == 3 or int(p) == 23:
			superscript = 'rd'
		else:
			superscript = 'th'
		return(str(p)+superscript+' of '+months[n]+' '+str(y))
	if form.getvalue("formatstyle") == 'both':
		if int(p) == 1 or int(p) == 21 or int(p) == 31:
			superscript = 'st'
		elif int(p) == 2 or int(p) == 22:
			superscript = 'nd'
		elif int(p) == 3 or int(p) == 23:
			superscript = 'rd'
		else:
			superscript = 'th'
		return(str(p)+superscript+' of '+months[n]+' '+str(y)+' or '+str(p) + "/" + str(n) + "/" + str(y))


print('Content-Type: text/html; charset=utf-8')
print('')
print('<!DOCTYPE html>')
print('<html>')
print('<head>')
print('<meta charset="utf-8">')
print('<link rel="stylesheet" type="text/css" href="design.css">')
print('<title> Python script to output the Easter Sunday </title>')
print('</head>')
print('<body>')
print('<h2>')
print('Easter sunday is on %s' % Easter(y))
print('</h2>')
print('</body>')
print('</html>')