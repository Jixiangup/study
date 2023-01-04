from urllib import request
import ssl
import certifi
from bs4 import BeautifulSoup

url = 'https://bnyte.github.io/'
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                  'Chrome/55.0.2883.87 Safari/537.36 '
}

page = request.Request(url, headers=headers)

page_info = request.urlopen(page, context=ssl.create_default_context(cafile=certifi.where()))

soup = BeautifulSoup(page_info, 'html.parser')

# html格式化打印当前页面
# print(soup.prettify())

#
titles = soup.select('.post-body > p')

for title in titles:
    # print(title)
    print(title.text)


