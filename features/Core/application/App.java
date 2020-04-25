package application;

import smarthome.Home;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub; 
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
		//(new Publisher()).start();
	}
	
	class Publisher extends Thread {
		String[] commands = {};
			
		
		@Override
		public void run() {
			super.run();
			
			Jedis jPublisher = null;
			try {
				jPublisher = App.getJedis();
				for (int i = 0; i < commands.length; i++) {
					try {
						Thread.sleep(1000);						
					} catch (Exception e) {
						e.printStackTrace();
					}
					Command c = new Command(commands[i]);
					//System.out.println("\n" + i + " Triggers " + messages[i][0]);
					jPublisher.publish(c.getChannel().toString(), c.toString());
					
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