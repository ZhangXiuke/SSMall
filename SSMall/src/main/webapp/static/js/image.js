var basepath = "";
$(function() {
	basepath = $("#basepath").val();
});

$('input[id=upfile]').localResizeIMG({
	width : 500,
	quality : 0.8,
	success : function(result) {
		
		//var img = new Image();
		$('#qwerqwer img').attr('src',result.base64);
		//img.src = result.base64;
		//alert("result.base64");
//		console.log(result.clearBase64);
		//$("body").append(img);
		//				$(".result").empty();
		//					$(".comImg").append(img); //呈现图像(拍照結果)
//		$(".comImg").attr("src", img.src); //呈现图像(拍照結果)
//		$(".comImg1").css('display', 'inline');
		var fileData = result.clearBase64;
		$.ajax({  
            url: basepath + "/hello/uploadPic",  
            type: "POST",  
            data:{fileData:fileData,licenceName:'Pcfile'},  
            dataType: "HTML",  
            timeout: 1000,  
            async:false,//是否异步  
            cache:false,//是否使用缓存
            error: function(){  
                alert("Error loading PHP document");  
            },  
             success: function(result){  
            	 if(result == null){
            		 alert("Uploads success~");
            	 }else{
            		// $(".comImg").attr("src", img.src); //呈现图像(拍照結果)
            		 $("#fileId").attr("value", result); //呈现图像(拍照結果)
            			//$(".comImg1").css('display', 'inline');
            	 }
            }  
        });
	}
});
$('input[id=refile]').localResizeIMG({
	width : 800,
	quality : 0.8,
	success : function(result) {
		var img = new Image();
		img.src = result.base64;
//		console.log(result.clearBase64);
		//$("body").append(img);
		//				$(".result").empty();
		//					$(".comImg").append(img); //呈现图像(拍照結果)
		var fileData = result.clearBase64;
		$.ajax({  
            url: basepath + "/base/upload/uploadPic",  
            type: "POST",  
            data:{fileData:fileData,licenceName:'Pcfile'},  
            dataType: "HTML",  
            timeout: 1000,  
            async:false,//是否异步  
            cache:false,//是否使用缓存
            error: function(){  
                alert("Error loading PHP document");  
            },  
             success: function(result){  
            	 if(result == null){
            		 alert("Uploads success~");
            	 }else{
            		 $(".comImg").attr("src", img.src); //呈现图像(拍照結果)
            		 $("#image1").attr("value", result); //呈现图像(拍照結果)
            			$(".comImg1").css('display', 'inline');
            	 }
            }  
        });
	}
});
$('input[id=refile1]').localResizeIMG({
	width : 800,
	quality : 0.8,
	success : function(result) {
		var img = new Image();
		img.src = result.base64;
//		console.log(result.clearBase64);
		//$("body").append(img);
		//				$(".result").empty();
		//					$(".comImg").append(img); //呈现图像(拍照結果)
		var fileData = result.clearBase64;
		$.ajax({  
            url: basepath +"/base/upload/uploadPic",  
            type: "POST",  
            data:{fileData:fileData,licenceName:'Pcfile'},  
            dataType: "HTML",  
            timeout: 1000,  
            async:false,//是否异步  
            cache:false,//是否使用缓存
            error: function(){  
                alert("Error loading PHP document");  
            },  
             success: function(result){  
            	 if(result == null){
            		 alert("Uploads success~");
            	 }else{
            		 $(".comImg1").attr("src", img.src); //呈现图像(拍照結果)
            		 $("#image2").attr("value", result); //呈现图像(拍照結果)
            			$(".comImg2").css('display', 'inline');
            	 }
            }  
        });
	}
});
$('input[id=refile2]').localResizeIMG({
	width : 800,
	quality : 0.8,
	success : function(result) {
		var img = new Image();
		img.src = result.base64;
//		console.log(result.clearBase64);
		//$("body").append(img);
		//				$(".result").empty();
		//					$(".comImg").append(img); //呈现图像(拍照結果)
		var fileData = result.clearBase64;
		$.ajax({  
            url: basepath + "/base/upload/uploadPic",  
            type: "POST",  
            data:{fileData:fileData,licenceName:'Pcfile'},  
            dataType: "HTML",  
            timeout: 1000,  
            async:false,//是否异步  
            cache:false,//是否使用缓存
            error: function(){  
                alert("Error loading PHP document");  
            },  
             success: function(result){  
            	 if(result == null){
            		 alert("Uploads success~");
            	 }else{
            		 $(".comImg2").attr("src", img.src); //呈现图像(拍照結果)
            		 $("#image3").attr("value", result); //呈现图像(拍照結果)
            			$(".comImg3").css('display', 'inline');
            	 }
            }  
        });
	}
});
$('input[id=refile3]').localResizeIMG({
	width : 800,
	quality : 0.8,
	success : function(result) {
		var img = new Image();
		img.src = result.base64;
//		console.log(result.clearBase64);
		//$("body").append(img);
		//				$(".result").empty();
		//					$(".comImg").append(img); //呈现图像(拍照結果)
		var fileData = result.clearBase64;
		$.ajax({  
            url: basepath + "/base/upload/uploadPic",  
            type: "POST",  
            data:{fileData:fileData,licenceName:'Pcfile'},  
            dataType: "HTML",  
            timeout: 1000,  
            async:false,//是否异步  
            cache:false,//是否使用缓存
            error: function(){  
                alert("Error loading PHP document");  
            },  
             success: function(result){  
            	 if(result == null){
            		 alert("Uploads success~");
            	 }else{
            		 $(".comImg3").attr("src", img.src); //呈现图像(拍照結果)
            		 $("#image4").attr("value", result); //呈现图像(拍照結果)
            			$(".comImg4").css('display', 'inline');
            	 }
            }  
        });
	}
});
$('input[id=refile4]').localResizeIMG({
	width : 800,
	quality : 0.8,
	success : function(result) {
		var img = new Image();
		img.src = result.base64;
//		console.log(result.clearBase64);
		//$("body").append(img);
		//				$(".result").empty();
		//					$(".comImg").append(img); //呈现图像(拍照結果)
		var fileData = result.clearBase64;
		$.ajax({  
            url: basepath + "/base/upload/uploadPic",  
            type: "POST",  
            data:{fileData:fileData,licenceName:'Pcfile'},  
            dataType: "HTML",  
            timeout: 1000,  
            async:false,//是否异步  
            cache:false,//是否使用缓存
            error: function(){  
                alert("Error loading PHP document");  
            },  
             success: function(result){  
            	 if(result == null){
            		 alert("Uploads success~");
            	 }else{
            		 $(".comImg4").attr("src", img.src); //呈现图像(拍照結果)
            		 $("#image5").attr("value", result); //呈现图像(拍照結果)
            			$(".comImg5").css('display', 'inline');
            	 }
            }  
        });
	}
});
