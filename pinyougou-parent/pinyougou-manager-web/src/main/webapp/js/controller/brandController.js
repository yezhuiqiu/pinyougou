 	app.controller("brandController",function($scope,$http,brandService){
    			
     		//查询品牌列表
    		$scope.findAll=function(){
    			
    			brandService.findAll().success(
    					
    			function(response){
    				
    				$scope.list=response
    				
    			}
    			);
    		}

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
		   	//$scope.findPage( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
   			 $scope.search( $scope.paginationConf.currentPage, $scope.paginationConf.itemsPerPage);
       		
       	}
    	
       	$scope.findPage=function(page,size){	
       		$http.get('../brand/findpage.do?page='+page+'&size='+size).success(
       				function(response){
       					$scope.list=response.rows;	
       					$scope.paginationConf.totalItems=response.total;//更新总记录数
       				}			
       		);
       	}
       	
       	
       	//保存
       	
       	$scope.save=function(){
       		
     		var methodName="add";
     		   	
       		if($scope.entity.id!=null){
       			methodName="update";
       		}  
       		
       		$http.post("../brand/"+methodName+".do",$scope.entity).success(
       				
       			function(response){
       				if(response.success){
       				 $scope.reloadList();//重新加载
       				alert(response.message);
       				}else{
       					alert(response.message);
       				}
       			}
       		
       		);
       		
       	}
       	
       	//查询实体
       	$scope.findOne=function(id){
       		
       		
       		
       		$http.get("../brand/findone.do?id="+id).success(
       				
       		function(response){
       			$scope.entity=response;
       		}
       		
       		);
       		
       	}
       	
       	//删除条目
       	$scope.del=function(id){
       		
       		$http.get("../brand/del.do?id="+id).success(
       		
       				function(response){
       					
       					if(response.success){
       					 $scope.reloadList();//重新加载
       						alert(response.message);
       					}else{
       						alert(response.message);
       					}
       				}
       		
       		);
       		
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
       	 
       	//批量删除
       	$scope.dellist=function(){
       		
       		$http.get("../brand/delList.do?ids="+$scope.selectIds).success(
       			
       				function(response){
       					if(response.success){
       					 $scope.reloadList();//重新加载
       					}else{
       						alert(response.message)
       					}
       				}
       		
       		);
       		
       		
       	}
       	$scope.searchEntity={};
       	
       	$scope.search=function(page,size){
       		
       		$http.post('../brand/search.do?page='+page+'&size='+size,$scope.searchEntity).success(
       				function(response){
       					$scope.list=response.rows;	
       					$scope.paginationConf.totalItems=response.total;//更新总记录数
       				}			
       		);
       		
       		
       	}
       	
		
	});