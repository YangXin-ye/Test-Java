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


    @Resource
    private RedisTemplate<String, String> redisTemplate;

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
        String cacheKey = PRODUCTS_CACHE_KEY + "_" + pageNum + "_" + pageSize;

        // 检查缓存
        Page<IndexCategoryNamesVO> productsPage;
        String redisData = redisTemplate.opsForValue().get(cacheKey);
        if ( redisData == null) {
            // 从数据库获取数据
            productsPage = productsService.listProductsPage(pageNum, pageSize, listProductsPageReq);

            // 存入缓存，设置缓存时间
            redisTemplate.opsForValue().set(cacheKey, JSON.toJSONString(productsPage), 10, TimeUnit.MINUTES);
        }else{
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
        clearCache();
        return Result.success();
    }

    /*
     ** 删
     */
    @PostMapping("/delete/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        productsService.deleteOrder(id);
        clearCache();
        return Result.success();
    }

    /*
     ** 改
     */
    @PostMapping("/update")
    public Result update(@RequestBody Products products) {
        productsService.updateOrder(products);
        clearCache();
        return Result.success();
    }

    // 清除缓存的方法
    private void clearCache() {
        Set<String> keys = redisTemplate.keys(PRODUCTS_CACHE_KEY + "_*");
        if (keys != null) {
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
