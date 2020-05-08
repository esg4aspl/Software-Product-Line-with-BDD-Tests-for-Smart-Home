package application;

import smarthome.Home;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import java.time.Duration;
import business.*;

/**
 * TODO description
 */
public class App {
	
	private static JedisPool pool;
	
	public static Jedis getJedis() {
		return pool.getResource();
	}
	
	public static void main(String[] args) {	
		App app = new App();
		app.init();
	}
	
	public void init() {
		final JedisPoolConfig poolConfig = buildPoolConfig();
		pool = new JedisPool(poolConfig, "localhost");
		Home home = new Home();
		home.init();
		(new Publisher()).start();
	}
	
	class Publisher extends Thread {
		String[] messages = {
				"ENV:CLOCK=07:00@HOME&TEMPERATURE=20@HOME&GLASSBREAK=Kitchen@HOME",
				"ENV:CLOCK=12:00@HOME&TEMPERATURE=20@HOME",
				"ENV:CLOCK=16:00@HOME&TEMPERATURE=20@HOME",
				"ENV:CLOCK=19:00@HOME&TEMPERATURE=21@HOME",
				"ENV:CLOCK=23:00@HOME&TEMPERATURE=21@HOME"
		};
			
		
		@Override
		public void run() {
			super.run();
			
			Jedis jPublisher = null;
			try {
				jPublisher = App.getJedis();
				for (int i = 0; i < messages.length; i++) {
					try {
						Thread.sleep(2000);						
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					jPublisher.publish("HOME", messages[i]);
					
				}
			} finally {
				if (jPublisher != null)
					jPublisher.close();
			}
			
		}
	}
	
	
	
	private static JedisPoolConfig buildPoolConfig() {
	    final JedisPoolConfig poolConfig = new JedisPoolConfig();
	    poolConfig.setMaxTotal(128);
	    poolConfig.setMaxIdle(128);
	    poolConfig.setMinIdle(16);
	    poolConfig.setTestOnBorrow(true);
	    poolConfig.setTestOnReturn(true);
	    poolConfig.setTestWhileIdle(true);
	    poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis());
	    poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis());
	    poolConfig.setNumTestsPerEvictionRun(3);
	    //poolConfig.setBlockWhenExhausted(true);
	    return poolConfig;
	}
	
	
	

}