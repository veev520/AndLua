from flask import Flask
import json

app = Flask(__name__)

@app.route('/1')
def lua1():
    f = open('Lua1.lua', 'r', encoding='UTF-8')
    content = f.read()
    f.close()
    data = {
        "id": 1,
        "type": "normal",
        "script": content
    }
    return json.dumps(data)


@app.route('/2')
def lua2():
    f = open('Lua2.lua', 'r', encoding='UTF-8')
    content = f.read()
    f.close()
    data = {
        "id": 2,
        "type": "custom",
        "script": content
    }
    return json.dumps(data)


@app.route('/list')
def list():
    l = []
    # 1
    with open('Lua1.lua', 'r', encoding='UTF-8') as f:
        lua_bean = {
            "id": 1,
            "version": 1,
            "type": "normal",
            "script": f.read()
        }
        bean = {
            "type": "lua",
            "data": lua_bean
        }
        l.append(bean)
    
    # 2
    test_bean1 = {
        "title": "Lua 特性",
        "subTitle": "可扩展",
        "content": "Lua提供了非常易于使用的扩展接口和机制：由宿主语言(通常是C或C++)提供这些功能，Lua可以使用它们，就像是本来就内置的功能一样。"
    }
    bean1 = {
        "type": "test1",
        "data": test_bean1
    }
    l.append(bean1)

    with open('Lua3.lua', 'r', encoding='UTF-8') as f:
        lua_bean = {
            "id": 3,
            "version": 1,
            "type": "custom",
            "script": f.read()
        }
        bean = {
            "type": "lua",
            "data": lua_bean
        }
        l.append(bean)

    # 3
    test_bean2 = {
        "title": "Lua 特性",
        "subTitle": "轻量级",
        "content": "它用标准C语言编写并以源代码形式开放，编译后仅仅一百余K，可以很方便的嵌入别的程序里。"
    }
    bean2 = {
        "type": "test1",
        "data": test_bean2
    }
    l.append(bean2)

    # 4
    with open('Lua2.lua', 'r', encoding='UTF-8') as f:
        lua_bean = {
            "id": 2,
            "version": 1,
            "type": "custom",
            "script": f.read()
        }
        bean = {
            "type": "lua",
            "data": lua_bean
        }
        l.append(bean)
    
    return json.dumps(l)


if __name__ == '__main__':
    app.debug = True
    app.run(host='0.0.0.0')