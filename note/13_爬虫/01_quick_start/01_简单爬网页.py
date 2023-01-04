# Beautiful Soup是一个可以从HTML或XML文件中提取结构化数据的Python库
from urllib import request
import certifi
from bs4 import BeautifulSoup
import ssl

# 构造头文件 模拟浏览器访问
url = "http://www.jianshu.com"
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
                  'Chrome/55.0.2883.87 Safari/537.36 '
}
# context = ssl._create_unverified_context()
page = request.Request(url, headers=headers)
# 打开Url,获取HttpResponse返回对象并读取其ResponseBody
page_info = request.urlopen(page, context=ssl.create_default_context(cafile=certifi.where())).read().decode('utf-8')

# 将获取到的内容转换成BeautifulSoup格式，并将html.parser作为解析器
soup = BeautifulSoup(page_info, 'html.parser')
# 以格式化的形式打印html
# print(soup.prettify())

titles = soup.find_all('a', 'title')  # 查找所有a标签中class='title'的语句

'''
# 打印查找到的每一个a标签的string和文章链接
    for title in titles:
        print(title.string)
        print("https://www.jianshu.com" + title.get('href'))   
'''
for title in titles:
    print(title.string)
    print("https://www.jianshu.com" + title.get('href'))

# open()是读写文件的函数,with语句会自动close()已打开文件
# with open(r"D:\Python\test\articles.txt", "w") as file:  # 在磁盘以只写的方式打开/创建一个名为 articles 的txt文件
#     for title in titles:
#         file.write(title.string + '\n')
#         file.write("http://www.jianshu.com" + title.get('href') + '\n\n')
