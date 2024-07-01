# pom文件新增redis依赖
```xml
		<dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
        <dependency>
            <groupId>com.fasterxml.jackson.datatype</groupId>
            <artifactId>jackson-datatype-jsr310</artifactId>
            <version>2.13.3</version>
        </dependency>
```
# application.yaml 添加redis配置
```yaml
  redis:
    host: localhost
    port: 6379
```
# 添加格式化配置类
```java
@Configuration
public class RedisConfig {


    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);

        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));

        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
}

```
# 进行修改商品的增删改查接口
```java
@RestController
@RequestMapping("/products")
public class ProductsController {

    @Resource
    private ProductsService productsService;


    //进行注入Redis使用属性
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    //进行声明变量
    private static final String PRODUCTS_CACHE_KEY = "products_page";




    /**
     * 分页查询所有数据
     *
     * @param listProductsPageReq 请求体
     * @param pageNum             当前页
     * @param pageSize            每页大小
     */
    @GetMapping("/list")
    public Result listUserPage(ListProductsPageReq listProductsPageReq,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        //进行组装redis的分页key
        String cacheKey = PRODUCTS_CACHE_KEY + "_" + pageNum + "_" + pageSize;

        Page<IndexCategoryNamesVO> productsPage;
        //从redis中进行查询，缓存的分页数据
        String redisData = redisTemplate.opsForValue().get(cacheKey);
        if ( redisData == null) {
            // 如果redis中的查询到是空，则进行查询数据库
            productsPage = productsService.listProductsPage(pageNum, pageSize, listProductsPageReq);

            // 查询数据库以后，进行缓存到redis
            redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(productsPage), 10, TimeUnit.MINUTES);
        }else{
            // redis数据不为空，直接设置值，进行返回
            productsPage = JSON.parseObject(redisData,Page.class);
        }
        return Result.success(productsPage);
    }

    /**
     * 增加数据
     *
     * @param products
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody AddProductsDTO addProductsDTO) {
        productsService.addProducts(addProductsDTO);
        //如果新增了一个新的数据，则调用清除缓存数据，进行吧redis中的缓存数据全部清除掉
        clearCache();
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        productsService.deleteOrder(id);
        //如果删除了一个新的数据，则调用清除缓存数据，进行吧redis中的缓存数据全部清除掉
        clearCache();
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Products products) {
        productsService.updateOrder(products);
        //如果修改了一个新的数据，则调用清除缓存数据，进行吧redis中的缓存数据全部清除掉
        clearCache();
        return Result.success();
    }

    // 清除Redis缓存的方法
    private void clearCache() {
        //获取到每一个分页的商品数据
        Set<String> keys = redisTemplate.keys(PRODUCTS_CACHE_KEY + "_*");
        if (keys != null) {
            //如果商品分页缓存的key不是空，则调用redis进行删除
            redisTemplate.delete(keys);
        }
    }

}
```
# 修改用户的增删改查接口
```java

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    private UsersService usersService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private static final String USER_CACHE_KEY = "user_page";

    @GetMapping("/userInfo")
    public Result getUserInfo(){
        System.out.println("userInfo");
        return Result.success(usersService.getUserInfo());
    }

    /**
     * 分页查询所有数据
     *
     * @param listUserPageReq 请求体
     * @param pageNum         当前页
     * @param pageSize        每页大小
     */
    @GetMapping("/list")
    public Result listUserPage(ListUserPageReq listUserPageReq,
                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize) {
        String cacheKey = USER_CACHE_KEY + "_" + pageNum + "_" + pageSize;

        // 检查缓存
        Page<Users> usersPage;
        String redisData = redisTemplate.opsForValue().get(cacheKey);
        if ( redisData == null) {
            usersPage = usersService.listUserPage(pageNum, pageSize, listUserPageReq);
            // 存入缓存，设置缓存时间
            redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(usersPage), 10, TimeUnit.MINUTES);
        }else{
            usersPage = JSON.parseObject(redisData,Page.class);
        }
        return Result.success(usersPage);
    }

    @PostMapping("/saveUser")
    public Result saveUser(@RequestBody Users users){
        usersService.saveUser(users);
        clearCache();
        return Result.success();
    }

    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody Users users){
        usersService.updateUser(users);
        clearCache();
        return Result.success();
    }

    @PostMapping("/deleteUser/{id}")
    public Result deleteUser(@PathVariable("id") Integer id){
        usersService.deleteUser(id);
        clearCache();
        return Result.success();
    }


    private void clearCache() {
        Set<String> keys = redisTemplate.keys(USER_CACHE_KEY + "_*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
```
