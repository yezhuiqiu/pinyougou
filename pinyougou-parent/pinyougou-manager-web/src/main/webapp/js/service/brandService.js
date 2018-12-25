app.service("brandService",function($http){
    		
    		this.findAll=function(){
    			return $http.get("../brand/findAll.do");
    		}
    		
    	
    		//下拉列表数据
    		this.selectOptionList=function(){
    			return $http.get('../brand/selectOptionList.do');
    		}
    		
    	});