import requests
import random
import time 

for i in range(0, 36000):
    t = random.randint(20,60)
    p = random.randint(60,100)
    c = random.randint(0,10) 

    URL = f'http://localhost:8080/ins?t={t}&p={p}&c={c}'
    print(URL)
    requests.get(URL)
    time.sleep(1)
