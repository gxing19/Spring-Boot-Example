local locks = require "resty.lock"
local function acquire()
    local lock = locks:new("locks")
    local elapsed, err = lock:lock("limit_key") --互斥锁
    local limit_counter = ngx.shared.limit_counter --计数器
    local key = "ip:" ..os.time()
    local limit = 5 --限流大小
    local current = limit_counter:get(key)

    if current ~= nil and current + 1 > limit then --超出限流
        lock:unlock()
        return 0
    end
    if current == nil then
        limit_counter:set(key, 1, 1) --第一次需要设置过期时间,值为1,1秒过期
    else
        limit_counter:incr(key, 1) --第二次开始加1
    end
    lock:unlock()
    return 1
end
ngx.print(acquire())
