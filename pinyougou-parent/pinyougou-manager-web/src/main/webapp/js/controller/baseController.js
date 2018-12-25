app.controller('baseController',function($scope){
	
	//分页控件配置 
	$scope.paginationConf = {
			 currentPage: 1,
			 totalItems: 10,
			 itemsPerPage: 10,
			 perPageOptions: [10, 20, 30, 40, 50],
			 onChange: function(){
			        	 $scope.reloadList();//重新加载
			 }
	}; 
	
 	$scope.reloadList=function(){
			 //切换页码  
			 $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
  		
  	}
	
  	$scope.selectIds=[];//用户选中的id集合
   	
    $scope.updateSelection=function($event,id){
   		
   		if($event.target.checked){
   			$scope.selectIds.push(id);//push方法向集合添加元素
   		}else{
       		var index=$scope.selectIds.indexOf(id);//查找值的位置
       		$scope.selectIds.splice(index,1);//参数1是移除的位置，参数2是移除的个数
   		}
   		
    
   	} 
    
    $scope.jsonToString=function(jsonString,key){
		var json=JSON.parse(jsonString);//将json字符串转换为json对象
		var value="";
		for(var i=0;i<json.length;i++){		
			if(i>0){
				value+=","
			}
			value+=json[i][key];			
		}
		return value;
	}
    
	
});