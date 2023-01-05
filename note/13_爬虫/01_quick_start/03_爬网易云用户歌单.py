#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import json
import logging
import ssl
import urllib.parse
from urllib import request
import certifi
import encrypt

url = 'https://music.163.com/weapi/v1/play/record?csrf_token='
headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (HTML, like Gecko) '
                  'Chrome/55.0.2883.87 Safari/537.36 ',
    'Content-Type': 'application/x-www-form-urlencoded'
}

# d = "{"uid":"313631290","type":"-1","limit":"1000","offset":"0","total":"true","csrf_token":""}"
# e = "010001"
# f =
#      "00e0b509f6259df8642dbc35662901477df22677ec152b5ff68ace615bb7b725152b3ab17a876aea8a5aa76d2e417629ec4ee341f56135fccf695280104e0312ecbda92557c93870114af6c9d05c4f7f0c3685b7a46bee255932575cce10b424d813cfe4875d3e82047b97ddef52741d546b8e289dc6935b3ece0462db0a22b8e7"
# g = "0CoJUm6Qyw8W8jud"
# body_source = {
#     'params': 'xk8qR6+Kty8L5HiH1VqcGN9+0CkDHSIm5SpKyqNQ2jFjE95e11exzWie61tBiWBRM4c8xhzmccVs+SowDxJHTz4CygjmdTrNNcvBcl3Lg4Gx7H+mYs1vSgJqU5TibEZ0',
#     'encSecKey': 'a7c685016fe97f799e63e47ac4841c2794c244ae22d214558f43c28b67f16a887659590ef80345cb1afb424adbe7e6b7d4f13ad2a0a0beb26c85b1977ee57df23bc9de3d07f56850f73eeac39aa9c2e00d53c71fd9bbf27d25f2ea92ada419634bdf8b5eee5eceb41e10a778e5f1a9c7a5f2eeb03cbb72da239b4a08259b18d6'
# }


body_source = '{"uid":"1728308878","type":"-1","limit":"1000","offset":"0","total":"true","csrf_token":""}'

body_encode = urllib.parse.urlencode(encrypt.get_form_data(body_source)).encode('utf-8')

song_info_req = request.Request(url, headers=headers, method='POST')

# 获取歌曲信息
song_info_resp = request.urlopen(song_info_req, context=ssl.create_default_context(cafile=certifi.where()),
                                 data=body_encode)

# 校验请求是否成功

if song_info_resp.getcode() == 200:
    datalines = song_info_resp.readlines()
    for data_bytes in datalines:
        data_json = json.loads(data_bytes.decode('utf-8'))
        # print(data_json['msg'])
        week_datas = data_json['weekData']
        for week_data in week_datas:
            song = week_data['song']
            singers = ''
            for singer in song['ar']:
                singers += (singer['name'] + '')
            print('歌曲名: {song_name}, 由{singers}演唱'.format(song_name=song['name'], singers=singers))
else:
    logging.error('获取用户歌曲信息失败')
