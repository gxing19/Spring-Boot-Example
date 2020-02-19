local key = KEYS[1]
local limit = ARGV[1]
local current = redis.call('get', key)
if (current) then
    if tonumber(current) + 1 > tonumber(limit) then
        return 0
    else
        redis.call("incrby", key, "1")
        return 1
    end
else
    redis.call("set", key, "1")
    redis.call("expire", key, "2")
    return 2
end