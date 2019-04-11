package com.java.nba.controller;

import com.java.nba.model.User;
import com.java.nba.service.IUserService;
import com.java.nba.util.RedisCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author daiyun 测试
 * @date 2019-4-9
 */
@Controller
@RequestMapping("/user")
public class UserController {

	private static final Logger log= LoggerFactory.getLogger(UserController.class);

	/*@Autowired按byType自动注入，而@Resource默认按 byName自动注入罢了。*/
	@Resource
	private IUserService userService;

	@Autowired
	private RedisCacheManager redisCacheManager;

	@RequestMapping(value="/test",method= RequestMethod.GET)
	public String test(HttpServletRequest request, Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		System.out.println("userId:"+userId);
		User user = null;
		if (userId==1) {
			user = new User();
			user.setAge(11);
			user.setId(1);
			user.setPassword("123");
			user.setUserName("java");
		}

		log.info(user.toString());
		model.addAttribute("user", user);
		return "index";
	}


	// /user/showUser?id=1
	@RequestMapping(value="/showUser",method=RequestMethod.GET)
	public String toIndex(HttpServletRequest request,Model model){
		int userId = Integer.parseInt(request.getParameter("id"));
		User user = this.userService.getUserById(userId);
		log.debug(user.toString());
		model.addAttribute("user", user);
		return "showUser";
	}

	// /user/showUser2?id=1
	@RequestMapping(value="/showUser2",method=RequestMethod.GET)
	public String toIndex2(@RequestParam("id") String id,Model model){
		int userId = Integer.parseInt(id);
		System.out.println("userId:"+userId);
		User user = this.userService.getUserById(userId);
		log.debug(user.toString());
		model.addAttribute("user", user);
		return "showUser";
	}


	// /user/showUser3/{id}
	@RequestMapping(value="/showUser3/{id}",method=RequestMethod.GET)
	public String toIndex3(@PathVariable("id")String id,Map<String, Object> model){
		int userId = Integer.parseInt(id);
		System.out.println("userId:"+userId);
		User user = this.userService.getUserById(userId);
		log.debug(user.toString());
		model.put("user", user);
		return "showUser";
	}

	// /user/{id}
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public @ResponseBody
	User getUserInJson(@PathVariable String id, Map<String, Object> model){
		int userId = Integer.parseInt(id);
		System.out.println("userId:"+userId);
		User user = this.userService.getUserById(userId);
		log.info(user.toString());
		return user;
	}

	// /user/{id}
	@RequestMapping(value="/jsontype/{id}",method=RequestMethod.GET)
	public ResponseEntity<User> getUserInJson2(@PathVariable String id, Map<String, Object> model){
		int userId = Integer.parseInt(id);
		System.out.println("userId:"+userId);
		User user = this.userService.getUserById(userId);
		log.info(user.toString());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	/**
	 * 设置redis存储
	 * 例如：http://localhost:8080/user/testSetRedis?key=1&value=testRedis
	 * @param key
	 * @param value
	 * @return
	 */
	@RequestMapping(value = "/testSetRedis")
	@ResponseBody
	public ResponseEntity<Boolean> testRedis(@RequestParam(value = "key") String key,
											 @RequestParam(value = "value") String value){
		boolean result = true;
		try {
			redisCacheManager.set(key, value);
			log.info("存入redis_key:{}", key);
		} catch (Exception e){
			log.error("存入redis异常",e);
			result = false;
		}

		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	/**
	 * 根据key值获取redis值
	 * 例如：http://localhost:8080/user/testGetRedis/1
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/testGetRedis/{key}")
	@ResponseBody
	public ResponseEntity<String> tesGetRedis(@PathVariable(value = "key") String key){
		String value = (String) redisCacheManager.get(key);
		log.info("根据key【{}】获取到值：{}", key, value);
		return new ResponseEntity<String>(value, HttpStatus.OK);
	}

}
