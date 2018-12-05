package com.cloud.common.util;

import lombok.NonNull;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 类名称：RedisConfiguration<br>
 * 类描述：Redis配置 <br>
 * 创建时间：2018年12月04日<br>
 *
 * @author 冯亚鹏
 * @version 1.0.0
 */
@Component
public class RedisUtil {
	
	private final RedisTemplate redisTemplate;
	
	private static Long DEFAULT_EXPIRE_TIMEOUT = 60 * 60 * 24 * 7L;
	
	public RedisUtil(RedisTemplate redisTemplate) {
		this.redisTemplate = redisTemplate;
	}
	
	/**
	 * 方法名: remove
	 * 方法描述:根据key删除对应缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param  key 缓存key
	 */
	public void remove(@NonNull String key) {
		redisTemplate.delete(key);
	}
	
	/***
	 * 方法名: removeAll
	 * 方法描述:清空所有缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 */
	public void removeAll(){
		redisTemplate.execute((RedisCallback<Void>) connection -> {
			connection.flushAll();
			return null;
		});
	}
	
	/**
	 * 方法名: flushDB
	 * 方法描述:清空DB <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 */
	public void flushDB(){
		redisTemplate.execute((RedisCallback<Void>) connection ->{
			connection.flushDb();
			return null;
		});
	}
	
	/**
	 * 方法名: getValue
	 * 方法描述:获取String缓存值 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.lang.Object
	 */
	public Object getValue(@NonNull String key){
		return redisTemplate.boundValueOps(key).get();
	}
	
	/**
	 * 方法名: getValueExpire
	 * 方法描述:获取指定String缓存的超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.lang.Long
	 */
	public Long getValueExpire(@NonNull String key){
		return redisTemplate.boundValueOps(key).getExpire();
	}
	
	/**
	 * 方法名: setValue
	 * 方法描述:设置String缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param value
	 */
	public void setValue(@NonNull String key,@NonNull Object value){
		redisTemplate.boundValueOps(key).set(value,DEFAULT_EXPIRE_TIMEOUT,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: setValueExpire
	 * 方法描述:设置String缓存的超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param expireTime
	 */
	public void setValueExpire(@NonNull String key,@NonNull Long expireTime){
		redisTemplate.boundValueOps(key).expire(expireTime, TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: appendValue
	 * 方法描述:追加String缓存值 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param appendValue
	 */
	public void appendValue(@NonNull String key,@NonNull String appendValue) {
		redisTemplate.boundValueOps(key).append(appendValue);
	}
	
	/**
	 * 方法名: getHash
	 * 方法描述:获取Hash缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.util.Map<java.lang.Object,java.lang.Object>
	 */
	public Map<Object, Object> getHash(@NonNull String key){
		return redisTemplate.boundHashOps(key).entries();
	}
	
	/**
	 * 方法名: getHashExpire
	 * 方法描述:获取Hash缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key 
	 * @return java.lang.Long
	 */
	public Long getHashExpire(@NonNull String key){
		return redisTemplate.boundHashOps(key).getExpire();
	}
	
	/**
	 * 方法名: getHashItem
	 * 方法描述:获取Hash缓存指定值 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param itemKey
	 * @return java.lang.Object
	 */
	public Object getHashItem(@NonNull String key,@NonNull String itemKey){
		return redisTemplate.boundHashOps(key).get(itemKey);
	}
	
	/**
	 * 方法名: setHash
	 * 方法描述:设置Hash缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param value
	 */
	public void setHash(@NonNull String key,@NonNull Map value){
		redisTemplate.boundHashOps(key).putAll(value);
		redisTemplate.boundHashOps(key).expire(DEFAULT_EXPIRE_TIMEOUT,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: setHashExpire
	 * 方法描述:设置Hash缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param expireTime
	 */
	public void setHashExpire(@NonNull String key,@NonNull Long expireTime){
		redisTemplate.boundHashOps(key).expire(expireTime,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: setHashItem
	 * 方法描述:设置Hash指定key的缓存值 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param itemKey
	 * @param itemValue
	 */
	public void setHashItem(@NonNull String key,@NonNull String itemKey,@NonNull String itemValue){
		redisTemplate.boundHashOps(key).put(itemKey,itemValue);
	}
	
	/**
	 * 方法名: removeHashItem
	 * 方法描述:删除Hash缓存的指定缓存元素 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param itemKey
	 */
	public void removeHashItem(@NonNull String key,@NonNull String itemKey) {
		redisTemplate.boundHashOps(key).delete(itemKey);
	}
	
	/**
	 * 方法名: getList
	 * 方法描述:获取List缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.util.List
	 */
	public List getList(@NonNull String key){
		return redisTemplate.boundListOps(key).range(0,redisTemplate.boundListOps(key).size());
	}
	
	/**
	 * 方法名: getListItem
	 * 方法描述:获取List缓存的指定元素 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param index
	 * @return java.lang.Object
	 */
	public Object getListItem(@NonNull String key,@NonNull Long index) {
		return redisTemplate.boundListOps(key).index(index);
	}
	
	/**
	 * 方法名: getListExpire
	 * 方法描述:获取List缓存的超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.lang.Long
	 */
	public Long getListExpire(@NonNull String key){
		return redisTemplate.boundListOps(key).getExpire();
	}
	
	/**
	 * 方法名: setList
	 * 方法描述:设置List缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param value
	 */
	public void setList(@NonNull String key,@NonNull List value){
		redisTemplate.boundListOps(key).rightPushAll(value);
		redisTemplate.boundListOps(key).expire(DEFAULT_EXPIRE_TIMEOUT,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: addListItem
	 * 方法描述:List缓存追加元素 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param itemValue
	 */
	public void addListItem(@NonNull String key,@NonNull Object itemValue){
		redisTemplate.boundListOps(key).rightPush(itemValue);
	}
	
	/**
	 * 方法名: setListExpire
	 * 方法描述:设置List缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param expireTime
	 */
	public void setListExpire(@NonNull String key,@NonNull Long expireTime) {
		redisTemplate.boundListOps(key).expire(expireTime,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: getSet
	 * 方法描述:获取Set缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.util.Set
	 */
	public Set getSet(@NonNull String key){
		return redisTemplate.boundSetOps(key).members();
	}
	
	/**
	 * 方法名: getSetExpire
	 * 方法描述:获取Set缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.lang.Long
	 */
	public Long getSetExpire(@NonNull String key){
		return redisTemplate.boundSetOps(key).getExpire();
	}
	
	/**
	 * 方法名: setSet
	 * 方法描述:设置Set缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param value
	 */
	public void setSet(@NonNull String key,@NonNull Set value){
		redisTemplate.boundSetOps(key).add(value);
		redisTemplate.boundSetOps(key).expire(DEFAULT_EXPIRE_TIMEOUT,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: addSetItem
	 * 方法描述:添加Set缓存元素 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param itemValue
	 */
	public void addSetItem(@NonNull String key,@NonNull Object itemValue){
		redisTemplate.boundSetOps(key).add(itemValue);
	}
	
	/**
	 * 方法名: setSetExpire
	 * 方法描述:设置Set缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param expireTime
	 */
	public void setSetExpire(@NonNull String key,@NonNull Long expireTime) {
		redisTemplate.boundSetOps(key).expire(expireTime,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: getGeo
	 * 方法描述:获取Geo（地理位置）缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.util.List<org.springframework.data.geo.Point>
	 */
	public List<Point> getGeo(@NonNull String key){
		return redisTemplate.boundGeoOps(key).position(key);
	}
	
	/**
	 * 方法名: getGeoExprie
	 * 方法描述:获取Geo（地理位置）缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @return java.lang.Long
	 */
	public Long getGeoExpire(@NonNull String key) {
		return redisTemplate.boundGeoOps(key).getExpire();
	}
	
	/**
	 * 方法名: setGeo
	 * 方法描述:设置Geo（地理位置）缓存 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param values
	 */
	public void setGeo(@NonNull String key,@NonNull List<RedisGeoCommands.GeoLocation> values) {
		values.stream().forEach(value -> redisTemplate.boundGeoOps(key).add(value));
		redisTemplate.boundGeoOps(key).expire(DEFAULT_EXPIRE_TIMEOUT,TimeUnit.SECONDS);
	}
	
	/**
	 * 方法名: setGeoExpire
	 * 方法描述:设置Geo（地理位置）缓存超时 <br>
	 * 创建时间: 2018-12-05<br>
	 * @author 冯亚鹏
	 * @param key
	 * @param expireTime
	 */
	public void setGeoExpire(@NonNull String key,@NonNull Long expireTime){
		redisTemplate.boundGeoOps(key).expire(expireTime,TimeUnit.SECONDS);
	}
	
}
