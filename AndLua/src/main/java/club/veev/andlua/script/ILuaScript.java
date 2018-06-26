package club.veev.andlua.script;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LuaValue;

/**
 * Created by Veev on 2018/6/26
 * Email: wangwei07@skyworth.com
 * Fun: lua 脚本 接口
 */
public interface ILuaScript {

    /**
     * 加载脚本
     * @param args      字符串脚本
     *                  文件名
     *                  输入流
     *                  Reader
     *                  URL 注意缓存
     */
    void load(Object ... args);

    /**
     * 获取脚本
     * @return      脚本
     */
    Globals getScript();

    /**
     * 获取ID
     * @return      ID
     */
    int getID();

    /**
     * 获取脚本的版本号
     * @return      版本号: 字符串形式
     */
    String getVersion();
}
