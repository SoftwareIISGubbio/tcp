# serve installare una libreria
# python3 -m pip install requests 
# poi su macOS si lamenta di qualcosa sulla versione delle librerie)


import requests
import random 

for i in range(0, 1000000):
    t = random.randint(20,60)
    p = random.randint(60,100)
    c = random.randint(0,10) 

    URL = f'http://localhost:8080/ins?t={t}&p={p}&c={c}'
    # print(URL)
    if ( i%1000==0 ):
        print(i)
    requests.get(URL)

# su un M2 pro 
# in 342 secondi 100000 insert => circa 290 insert al secondo