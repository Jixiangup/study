# 最基础的一个match模块
def http_error00(status):
    match status:
        case 400:
            return "Bad request"
        case 404:
            return "Not found"
        case 418:
            return "I'm a teapot"
        case _:
            return "Something's wrong with the internet"


print(f'http_error01 execute result >> {http_error00(400)}')


# 带有 | 的多值通配模块
def http_error01(status):
    match status:
        case 400 | 403 | 401:
            return "Bad request"
        case 404:
            return "Not found"
        case 418:
            return "I'm a teapot"
        case _:
            return "Something's wrong with the internet"


print(f'http_error01 execute result >> {http_error01(401)}')


class Point:

    def __init__(self):
        pass

    def __init__(self, x, y):
        self.x = x
        self.y = y

    x: int
    y: int

point = Point(1, 0)

def where_is(point):
    match point:
        case Point(x=0, y=0):
            print("Origin")
        case Point(x=0, y=y):
            print(f"Y={y}")
        case Point(x=x, y=0):
            print(f"X={x}")
        case Point():
            print("Somewhere else")
        case _:
            print("Not a point")

where_is(point)