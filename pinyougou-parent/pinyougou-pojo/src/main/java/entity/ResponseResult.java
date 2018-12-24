package entity;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 响应结构
 */
public class ResponseResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    // 响应业务状态
    private Integer status;

    // 响应消息
    private String message;

    // 响应中的数据
    private Object data;
    
    private Long total;//总条数
    
    //列表
	private List rows;
	
	private boolean success;
    


	private  Long totalPages;//总页数
    
	
	
	
    public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public List getRows() {
		return rows;
	}

	public ResponseResult setRows(List rows) {
		this.rows = rows;
		return this;
	}

    public Long getTotal() {
		return total;
	}

	public ResponseResult setTotal(Long total) {
		this.total = total;
		
		return this;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public ResponseResult setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
		
		return this;
	}

	public static ResponseResult build(Integer status, String message, Object data) {
        return new ResponseResult(status, message, data);
    }
	
	public static ResponseResult build(boolean success, String message) {
        return new ResponseResult(success, message);
    }
	
	public static ResponseResult build(Integer status, String message, List list) {
        return new ResponseResult(status, message, list);
    }

    public static ResponseResult ok(Object data) {
        return new ResponseResult(data);
    }

    public static ResponseResult ok() {
        return new ResponseResult(null);
    }

    public ResponseResult() {

    }

    public static ResponseResult build(Integer status, String message) {
        return new ResponseResult(status, message, null);
    }

    public ResponseResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
    public ResponseResult(Integer status, String message, List list) {
        this.status = status;
        this.message = message;
        this.rows = list;
    }

    public ResponseResult(Object data) {
        this.status = 200;
        this.message = "OK";
        this.data = data;
    }

    public ResponseResult(boolean success, String message) {
    	this.success = success;
    	this.message = message;
	}

//    public Boolean isOK() {
//        return this.status == 200;
//    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getmessage() {
        return message;
    }

    public void setmessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 将json结果集转化为TaotaoResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static ResponseResult formatToPojo(String jsonData, Class<?> clazz) {
        try {
            if (clazz == null) {
                return MAPPER.readValue(jsonData, ResponseResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null) {
                if (data.isObject()) {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                } else if (data.isTextual()) {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static ResponseResult format(String json) {
        try {
            return MAPPER.readValue(json, ResponseResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static ResponseResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("message").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
