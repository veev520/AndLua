context = ...

local LinearLayout = luajava.bindClass('android.widget.LinearLayout')
local LayoutParams = luajava.bindClass('android.widget.LinearLayout$LayoutParams')
local TextView = luajava.bindClass('android.widget.TextView')
local Button = luajava.bindClass('android.widget.Button')
local ImageView = luajava.bindClass('android.widget.ImageView')
local Gravity = luajava.bindClass('android.view.Gravity')
local Color = luajava.bindClass('android.graphics.Color')
local R = luaandroid.bindClass('club.veev.andluademo.R')

linearLayout = LinearLayout.new(context)
linearLayout:setOrientation(LinearLayout.VERTICAL)
linearLayout:setBackgroundColor(Color.WHITE)

layoutParams = LayoutParams.new(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
-- layoutParams:setMarginStart(dp2px(16))
-- layoutParams:setMarginEnd(dp2px(16))
linearLayout:setLayoutParams(layoutParams)
linearLayout:setGravity(Gravity.CENTER_HORIZONTAL)
linearLayout:setPadding(dp2px(16), dp2px(16), dp2px(16), dp2px(16))

wrapParams = LayoutParams.new(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

textView = TextView.new(context)
textView:setText("Hello 183")
textView:setTextColor(0xFFFF0000)
textView:setLayoutParams(wrapParams)

textView2 = TextView.new(context)
textView2:setText("你好 666")
textView2:setTextColor(0xFF800080)
textView2:setLayoutParams(wrapParams)

btn = Button.new(context)
btn:setText("Click")
btn:setTextColor(Color.YELLOW)
btnParams = LayoutParams.new(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
btn:setLayoutParams(wrapParams)
btnClick = {}
function btnClick.onClick(v)
	toast('Click Me')
	if (img:isShown())
	then
		img:setVisibility(0x08)
	else
		img:setVisibility(0x00)
	end
end
local btnListener = luajava.createProxy('android.view.View$OnClickListener', btnClick)
btn:setOnClickListener(btnListener)

img = ImageView.new(context)
img:setImageResource(R.mipmap.ic_launcher)

linearLayout:addView(textView)
linearLayout:addView(btn)
linearLayout:addView(img)
linearLayout:addView(textView2)

function getView()
	return linearLayout
end