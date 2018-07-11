-- Simple lua script that is executed when the activity start.
--
-- Arguments are the activity and the view.
local activity, view = ...
print('activity', activity, 'view', view)

function init()
	view:setMinimumHeight(dp2px(320))
end

-- forward declarations of local functions and variables
local animate,render
local chars = {}
local W, H = 600, 800

-- called on touch event
function onTouchEvent(event)
	-- print('onTouchEvent', event)
	if event:getAction() ~= 0 then 
		return true 
	end
	local x1, y1 = event:getX(), event:getY()
	print(x1, y1)
	while #chars > 9 do table.remove(chars, 1) end
	table.insert(chars, {
		x=x1,	-- 横坐标
		y=y1,	-- 纵坐标
		xi=3,	-- 横向加速度
		yi=5,	-- 纵向加速度
		n=0,	-- 碰撞次数
		r=math.random(20,50)})
	return true
end

-- called to draw the lua view
local prev, interval = os.time(), 1/60
local Thread = luajava.bindClass('java.lang.Thread')
function onDraw(canvas)
	view:invalidate()
	local curr = os.time()
	local diff = curr - prev
	if diff >= interval then
		pcall(animate, canvas)
		local wait = math.floor(1000 * (prev + interval - os.time()))
		if wait > 0 then pcall(Thread.sleep, Thread, wait) end
		prev = os.time()
	end
	pcall(render, canvas)
end

-- the animation step moves the line endpoints
local xi,yi = 0,0
local vxi,vyi = 3,1
local w, h = W, H
local advance = function(x,vx,max,rnd, count)
	x = x + vx
	if x < rnd then
		return rnd, math.random(1,15), count + 1
	elseif x > max - rnd then
		return max - rnd, math.random(-15,-1), count + 1
	end
	return x, vx, count
end
animate = function(canvas)
	xi,vxi = advance(xi,vxi,w, 40, -999)
	yi,vyi = advance(yi,vyi,h, 40, -999)
	while #chars > 0 and chars[1].n > 9 do
		table.remove(chars, 1)
	end
	for i,c in pairs(chars) do
		c.x, c.xi, c.n = advance(c.x, c.xi, w, c.r, c.n)
		c.y, c.yi, c.n = advance(c.y, c.yi, h, c.r, c.n)
	end
end

-- classes that we need for rendering
local Color = luajava.bindClass('android.graphics.Color')
local Paint = luajava.bindClass('android.graphics.Paint')
local Style = luajava.bindClass('android.graphics.Paint$Style')
print('Color, Paint, Style', Color, Paint, Style)

-- colors that we use
local line_color, circle_color = Paint.new(), Paint.new()
line_color:setColor(0xffffaa33)
line_color:setStrokeWidth(1.5)
line_color:setStyle(Style.STROKE)
line_color:setAntiAlias(true)
circle_color:setColor(0xff88aa33)
circle_color:setStrokeWidth(1.5)
circle_color:setStyle(Style.STROKE)
circle_color:setAntiAlias(true)

-- the render step draws the scene
render = function(canvas)
	-- scale the drawing to approximagely 600 x 800
 	W, H = canvas:getWidth(), canvas:getHeight();
 	local scale = (W + H) / (600 + 800)
 	canvas:scale(scale, scale)
 	w, h = W / scale, H / scale

	-- redraw the canvas
	canvas:drawColor(0xff112244)

	-- line
	canvas:drawCircle(xi, yi, 40, line_color)

	for i,c in pairs(chars) do
		canvas:drawCircle(c.x, c.y, c.r, circle_color)
	end
end

