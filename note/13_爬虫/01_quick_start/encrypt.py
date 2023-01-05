#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import base64
import codecs
import random

from Crypto.Cipher import AES

"""
网易云请求参数反编译工具
    :主要断点观察js,改为python实现
"""

emj = {
    "色": "00e0b",
    "流感": "509f6",
    "这边": "259df",
    "弱": "8642d",
    "嘴唇": "bc356",
    "亲": "62901",
    "开心": "477df",
    "呲牙": "22677",
    "憨笑": "ec152",
    "猫": "b5ff6",
    "皱眉": "8ace6",
    "幽灵": "15bb7",
    "蛋糕": "b7251",
    "发怒": "52b3a",
    "大哭": "b17a8",
    "兔子": "76aea",
    "星星": "8a5aa",
    "钟情": "76d2e",
    "牵手": "41762",
    "公鸡": "9ec4e",
    "爱意": "e341f",
    "禁止": "56135",
    "狗": "fccf6",
    "亲亲": "95280",
    "叉": "104e0",
    "礼物": "312ec",
    "晕": "bda92",
    "呆": "557c9",
    "生病": "38701",
    "钻石": "14af6",
    "拜": "c9d05",
    "怒": "c4f7f",
    "示爱": "0c368",
    "汗": "5b7a4",
    "小鸡": "6bee2",
    "痛苦": "55932",
    "撇嘴": "575cc",
    "惶恐": "e10b4",
    "口罩": "24d81",
    "吐舌": "3cfe4",
    "心碎": "875d3",
    "生气": "e8204",
    "可爱": "7b97d",
    "鬼脸": "def52",
    "跳舞": "741d5",
    "男孩": "46b8e",
    "奸笑": "289dc",
    "猪": "6935b",
    "圈": "3ece0",
    "便便": "462db",
    "外星": "0a22b",
    "圣诞": "8e7",
    "流泪": "01000",
    "强": "1",
    "爱心": "0CoJU",
    "女孩": "m6Qyw",
    "惊恐": "8W8ju",
    "大笑": "d"
}

md = ["色", "流感", "这边", "弱", "嘴唇", "亲", "开心", "呲牙", "憨笑", "猫", "皱眉", "幽灵", "蛋糕", "发怒", "大哭",
      "兔子", "星星", "钟情", "牵手",
      "公鸡", "爱意", "禁止", "狗", "亲亲", "叉", "礼物", "晕", "呆", "生病", "钻石", "拜", "怒", "示爱", "汗", "小鸡",
      "痛苦", "撇嘴", "惶恐", "口罩",
      "吐舌", "心碎", "生气", "可爱", "鬼脸", "跳舞", "男孩", "奸笑", "猪", "圈", "便便", "外星", "圣诞"]


def get_bq_n1x(keys):
    m0x = []
    for key in keys:
        m0x.append(emj[key])
    return ''.join(m0x)


def __get_random_str():
    """
    Returns:16位的随机字符串

    """
    str_set = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    random_str = ""
    for i in range(16):
        index = random.randint(0, len(str_set) - 1)
        random_str += str_set[index]
    return random_str


arg2 = get_bq_n1x(["流泪", "强"])
arg3 = get_bq_n1x(md)
arg4 = get_bq_n1x(["爱心", "女孩", "惊恐", "大笑"])
random_str = __get_random_str()


def __aes_encrypt(text, key):
    """
    获取到ASW加密后的数据
    Args:
        text: 首先CBC加密方法，text必须位16位数据
        key: 加密的key

    Returns:加密后的字符串

    """
    # 加密或者解密的初始向量（16位）
    iv = "0102030405060708"
    # 不是16的倍数则填充
    pad = 16 - len(text) % 16
    if isinstance(text, str):
        text = text + pad * chr(pad)
    else:
        text = text.deocde("utf-8") + pad * chr(pad)
    aes = AES.new(key=bytes(key, encoding="utf-8"), mode=2, iv=bytes(iv, encoding="utf-8"))
    res = aes.encrypt(bytes(text, encoding="utf-8"))
    res = base64.b64encode(res).decode("utf-8")
    return res


def __get_enc_text(arg1):
    """
    对称加密后的参数
    Args:
        arg1:加密参数

    Returns:

    """
    enc_text = __aes_encrypt(arg1, arg4)
    enc_text = __aes_encrypt(enc_text, random_str)
    return enc_text


def __get_enc_sec_key():
    """
    对称加密密钥

    通过查看js代码，获取encSecKey
    """

    #  随机字符串逆序排列
    text = random_str[::-1]
    rs = int(codecs.encode(text.encode('utf-8'), 'hex_codec'), 16) ** int(arg2, 16) % int(arg3, 16)
    return format(rs, 'x').zfill(256)


def linux_encrypt(text):
    # print(text)
    return text


def get_form_data(text, method=''):
    """
    反编译生成
    请求的form-data参数
    Args:
        text: 跟踪js,自己组装参数
        method: 方法

    Returns:form-data参数

    """
    """

    """
    if method == 'linux':
        return linux_encrypt(text)
    text = str(text)

    return {"params": __get_enc_text(text), "encSecKey": __get_enc_sec_key()}


if __name__ == '__main__':
    print(get_form_data('{"uid":"313631290","type":"-1","limit":"1000","offset":"0","total":"true","csrf_token":""}'))
