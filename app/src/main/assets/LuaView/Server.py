from flask import Flask
app = Flask(__name__)

@app.route('/1')
def lua1():
    f = open('Lua1.lua', 'r', encoding='UTF-8')
    content = f.read()
    f.close()
    return content


@app.route('/2')
def lua2():
    f = open('Lua2.lua', 'r', encoding='UTF-8')
    content = f.read()
    f.close()
    return content


if __name__ == '__main__':
    app.run(host='0.0.0.0')