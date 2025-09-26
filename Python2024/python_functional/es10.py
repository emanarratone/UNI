def mappreconcat(pref, lis):
    return map(lambda x: pref + x, lis)


pre = 'gelato'
post = ['panna', 'crema', 'cioccolato']
lis_new = list(mappreconcat(pre, post))
print(lis_new)

