




/*---------------------上傳圖片--------------------------*/
oFReader = new FileReader(),rFilter = /^(?:image\/bmp|image\/cis\-cod|image\/gif|image\/ief|image\/jpeg|image\/jpeg|image\/jpeg|image\/pipeg|image\/png|image\/svg\+xml|image\/tiff|image\/x\-cmu\-raster|image\/x\-cmx|image\/x\-icon|image\/x\-portable\-anymap|image\/x\-portable\-bitmap|image\/x\-portable\-graymap|image\/x\-portable\-pixmap|image\/x\-rgb|image\/x\-xbitmap|image\/x\-xpixmap|image\/x\-xwindowdump)$/i;
oFReader.onload = function (oFREvent) {
  document.querySelector(".uploadPreview").src = oFREvent.target.result; 
};

function loadImageFile() {
	var oFile = document.getElementById("uploadImage").files[0];
	if (document.getElementById("uploadImage").files.length === 0){ return;}
  if (!rFilter.test(oFile.type)) { alert("You must select a valid image file!"); return; }
  oFReader.readAsDataURL(oFile);
};
function loadImageFile2() {
	var oFile = document.getElementById("uploadImage2").files[0];
	if (document.getElementById("uploadImage2").files.length === 0){ return;}
	if (!rFilter.test(oFile.type)) { alert("You must select a valid image file!"); return; }
	 oFReader.readAsDataURL(oFile);
};
/**********祥的JS**********/
$(function(){


/*------------------查詢食品飲品----------------------------*/
    var food =[
        "炒飯",
        "貢丸",
        "香腸",
        "滷蛋"
    ];
    $('.search_food').autocomplete({
        source: food
    });

    var drink =[
        "可樂",
        "綠茶",
        "奶茶",
        "咖啡",
        "紅茶"
    ];
    $('.search_drink').autocomplete({
        source: drink
    });

    /*------------------儲值資料查詢----------------------------*/ 
    var arr= new Array();
    var i=0;
    $("#userinfo").focus(function(){
      $.ajax({
            url:"./FindUser",
            type:"POST",
            dataType:"json",
            success:function(data){
            while (i<data.length){ 
                  arr.push('帳號:'+data[i].account+','
                              +'姓名:'+data[i].name+','
                              +'電話:'+data[i].phone);i++;}
           }
        });
    });
   
    $('#userinfo').autocomplete({
        source: arr
    });
    
    /*------------------儲值資料查詢----------------------------*/ 
    
    /*------------------儲值資料顯示----------------------------*/ 
    
  
    $('#try').click(function(){
    	 var info= new Array();
      var search = $('#userinfo');
      $.ajax({
          url:"./CashInfoCheck",
          type:"POST",
          data:search,
          dataType:"json",
          success:function(data){
                
            info.push('帳號:'+data[0].account+'<br> <br>'
                        +'姓名:'+data[0].name+'<br> <br>'
                        +'電話:'+data[0].phone+'<br> <br>'
                        +'餘額:'+data[0].money);
              $('.cashinfo').empty();
              $('.cashinfo').prepend('<h3 id="result">'+info[0]+'</h3>'+'\n');
              $(".search_result").css('display','inline');
          }
      });
        
        
     });
    
    
    
    
    

    
    /*------------------儲值資料顯示----------------------------*/ 
    
    $('#cash').click(function(){
       var usr= new Array();
      var search = $('#userinfo');
      var usr = $('#userinfo').val().split(",")[0];
      console.log(usr);
      
      var cashmoney=$('#cashmoney');
            var cash = alertify.confirm(search.val()+'<br>確定加值  '+cashmoney.val()+'  元?',function(e){
            	
            	
            	if (e){
                
                $.ajax({
                    url:"./Cash",
                    type:"POST",
                    data:{"usr":usr,"money":cashmoney.val()},
                    success:function(data){
                    	if(data=='true'){
                    		alertify.success('加值成功 頁面跳轉中 請稍候');
                		setTimeout(function(){ window.location = './cash.jsp'; }, 2000);
                		}
                    		
                    	else{
                    		alertify.error('加值失敗 頁面跳轉中 請稍候');
                    	setTimeout(function(){ window.location = './cash.jsp'; }, 3000);
                    	}
                    	
                      
                    }
                });
                
            } });
      
      
    });
    
    
    
    $('#cancal').click(function(){
    	$('#result').remove();
    	$(".search_result").css('display','none');
    	
    });
    
//*------------------顯示食品、飲品選項-----------------------------*/
    $('#buildBtn_toggle').click(function(){
        $('.showHide_list').fadeToggle();
    });




/*------------------顯示搜尋食品、飲品Div-----------------------------*/
    $('#search_Btn').click(function(){
        $('#search_food_drink').fadeToggle();
    });



/*------------------顯示食品、飲品Div-----------------------------*/
    $('#drink').click(function(){
    	location.href='./buildItemDrink.jsp';	

    });
    $('#food').click(function(){
    	location.href='./buildItemFood.jsp';
    });


/*------------------隱藏食品、飲品Div-----------------------------*/
    $('.turnoff').click(function(){
        $('#showHideDrink').hide();
        $('#showHideFood').hide();
        $('#search_food_drink').hide();
        $('#build_food_drink_button').hide();
    });




});
/**********底下是黃少鴻鴻的JS**********/

$(function(){
      var tag_list = [];
      var jCloudObj={};
      var jCloudName=[];
      var jCloudWeight=[];
      var jCloudLink=[];
      /*--設定jQcloud--*/
         function  setJQcloud( jCloudName,jCloudWeight,jCloudLink) {
                for (var i = 0; i <jCloudName.length; i++) {
                  jCloudObj={};//初始化b
                  jCloudObj.text=jCloudName[i];
                  jCloudObj.weight=jCloudWeight[i];
                  jCloudObj.link='./single.jsp?order='+jCloudLink[i];
                  /*var jsonText = JSON.stringify(jCloudObj, "\t");
                  document.write(jsonText);*/
                  tag_list.push(jCloudObj);
                 // console.log('jCloudLink[i]= '+jCloudLink[i]);
              };
              $("#my_favorite_latin_words").jQCloud(tag_list, {
                  // 套用標籤雲並延遲載入標籤
                  // 同時隨機幫每一個標籤加上 r1, r2, r3 的 className
                  width: 307.795,
                  height: 350,
                  autoResize: true,
                  delayedMode: true
              });
          }
         
      $.ajax({
              url : './jQcloudAjax',
              dataType : 'json',
              success : function(data) {
                  //console.log(data);
                  var countJQ=[];
                  var howMuchItemNumber=[];
                  var countHowMuch=0;
                  var inverse=[];
                  //var itemNumOrder=[];
                  for(var i in data){
                        var iparse=parseInt(data[i].itemInformation.itemNumber);
                        if(countJQ[iparse]===undefined){
                              countJQ[iparse]=0;
                        }
                        countJQ[iparse]=countJQ[iparse]+data[i].count;
                        if($.inArray(iparse,howMuchItemNumber)==-1){
                              howMuchItemNumber[countHowMuch]=iparse;
                              inverse[iparse]=i;//將data的i存進INVERSE的商品編號
                              //itemNumOrder[iparse]=data[i].buildOrder.orderNumber;
                              countHowMuch++;
                        }
                  }
                  for(var i=0;i<howMuchItemNumber.length;i++){
                        var hminum=howMuchItemNumber[i];//商品編號
                        var invs=inverse[hminum]; //商品編號對應到的data[i] 
                        //var invOrd=itemNumOrder[hminum];//商品編號對應到的訂單編號           
                      jCloudName[i]=data[invs].itemInformation.name;
                      jCloudWeight[i]=countJQ[hminum];
                      jCloudLink[i]=data[invs].buildOrder.orderNumber;
                     // console.log('hminum= '+hminum);
                  }
                  setJQcloud(jCloudName,jCloudWeight,jCloudLink);
              }
          });

  
/**********判定為移動端時**********/
    var bodyWidth=$('body').width();
    var browser={
    versions:function(){
        var u = navigator.userAgent, app = navigator.appVersion;
        return {
            trident: u.indexOf('Trident') > -1, //IE
            presto: u.indexOf('Presto') > -1, //opera
            webKit: u.indexOf('AppleWebKit') > -1, //apple google
            gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//firefox
            mobile: !!u.match(/AppleWebKit.*Mobile.*/), //mobile
            ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios
            android: u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android
            iPhone: u.indexOf('iPhone') > -1 , //iPhone QQHD browser
            iPad: u.indexOf('iPad') > -1, //iPad
            webApp: u.indexOf('Safari') == -1, 
            weixin: u.indexOf('MicroMessenger') > -1, 
            qq: u.match(/\sQQ/i) == " qq" 
        };
    }(),
    language:(navigator.browserLanguage || navigator.language).toLowerCase()
    }
    if(browser.versions.mobile||browser.versions.android||browser.versions.ios){
     $('#my_favorite_latin_words').addClass('hide');
     $('.logo').addClass('hide');
    }else{
        $('#my_favorite_latin_words').removeClass('hide');
        $('.logo').removeClass('hide');
    }

    if (bodyWidth<1024&&bodyWidth>640) {
        $('.banner-bag').css('padding-left','65px');
        $('.banner-off').css('padding-left','65px');            
    }else{
        $('.banner-bag').css('padding-left','15px');
        $('.banner-off').css('padding-left','15px');
    }
        /*--選單列-*/
   if($('.sub-cate').width()>350){
        $('.menu-chain').addClass('hide');
        $('.view-all').addClass('hide');
        $('.glyphicon-align-justify').removeClass('hide');
        $('.menu').css('display','none');
        }else{
             $('.menu-chain').removeClass('hide');
             $('.view-all').removeClass('hide');
             $('.glyphicon-align-justify').addClass('hide');
             $('.menu').css('display','');
        }
    $(document).on("click",'.glyphicon-align-justify', function () {
            $('.menu').slideToggle();
        });
});


/**********當螢幕放大或縮小時**********/
    $(window).resize(function() {
         /*--變小再放大標籤雲消失-*/
        var menuChainWidth=$('.menu-chain').width(),
            menuChainheight=$('.menu-chain').height(),
            bodyWidth=$('body').width();
            $('#my_favorite_latin_words').css('width', menuChainWidth+'px' );
            $('#my_favorite_latin_words').css('height', menuChainheight+'px' );
            var divCon=$('.main').width(),
            item_img=$('.mark_item_img').width(),
            posi_div=(divCon-item_img)/2;
            $('.mark_foodtop').css('left',posi_div);
             /*--選單列-*/
         if($('.sub-cate').width()>350){
        $('.menu-chain').addClass('hide');
        $('.view-all').addClass('hide');
        $('.glyphicon-align-justify').removeClass('hide');
        $('.menu').css('display','none');
        }else{
             $('.menu-chain').removeClass('hide');
             $('.view-all').removeClass('hide');
             $('.glyphicon-align-justify').addClass('hide');
             $('.menu').css('display','');
        }
    });
/*跑馬燈*/
$(function(){
    var pp=$('.ppt_all'),
        bPicLi=$('.powerpoint_big_pic>ul>li'),
        textLi=$('.powerpoint_textlist>ul>li'),
        bPicLength=bPicLi.length;
    showpic(0);
    var t=setInterval(function(){
        play()
        },2500);
    pp.hover(function(){
        clearInterval(t);
        },function(){t=setInterval(function(){play()},2500);});

    for (var i = 0; i <bPicLength; i++) {
        $('.powerpoint_right>ul>li').eq(i).hover(function(){
            //bPicLi.eq(now).stop(true);
            now=$(this).index();
            insertArrow(now);
            showpicImmidiataly(now);
        });
    }

    var now=1;
    function play(){
        showpic(now);
        insertArrow(now)
        now++;
        if (now==bPicLength) {now=0;}
    }

    function showpic(inow){
        for (var j = 0; j <bPicLength; j++) {
            bPicLi.eq(j).stop(false,false).animate({opacity:'0'},500,function(){bPicLi.eq(j).addClass('hidden');});
        }
        bPicLi.eq(inow).removeClass('hidden');
        bPicLi.eq(inow).stop(false,false).animate({opacity:'1'},500);
    }

    function showpicImmidiataly(inow){
        for (var j = 0; j <bPicLength; j++) {
            bPicLi.eq(j).addClass('hidden');
        }
        bPicLi.eq(inow).removeClass('hidden');
        bPicLi.eq(inow).css('opacity','1');
    }

    function insertArrow(inow){
        for (var j = 0; j <bPicLength; j++) {
            $('#ppt_r'+j).removeClass('glyphicon-play');
        }
        $('#ppt_r'+inow).addClass('glyphicon-play');
    }
});
/**********底下是陳湞欽的JS**********/
$(function(){
	$('#Apwd').on('click', function(){
		if($('.Apwd').hasClass('hidden')){
			$('.AOths').addClass('hidden');
			$('.Apwd').removeClass('hidden');
		} else {
			$('.Apwd').addClass('hidden');
		};
	});
	$('#Aname').on('click', function(){
		if($('.Aname').hasClass('hidden')){
			$('.AOths').addClass('hidden');
			$('.Aname').removeClass('hidden');
		} else {
			$('.Aname').addClass('hidden');
		};
	});
	$('#Aph').on('click', function(){
		if($('.Aph').hasClass('hidden')){
			$('.AOths').addClass('hidden');
			$('.Aph').removeClass('hidden');
		} else {
			$('.Aph').addClass('hidden');
		};
	});
	$('#Amail').on('click', function(){
		if($('.Amail').hasClass('hidden')){
			$('.AOths').addClass('hidden');
			$('.Amail').removeClass('hidden');
		} else {
			$('.Amail').addClass('hidden');
		};
	});
	$('#sendpwd').on('click',function(){
		if(confirm('確認變更密碼?')){
			$('#pwdform').submit();
		} else {
			alert('變更已取消');
			location.reload();
		}
	});
	$('#sendname').on('click',function(){
		if(confirm('確認更改姓名為："'+$('#Nname').val()+'" ?')){
			$('#nameform').submit();
		} else {
			alert('變更已取消');
		}
	});
	$('#sendph').on('click',function(){
		if(confirm('確認更改電話號碼為："'+$('#Nph').val()+'" ?')){
			$('#phoneform').submit();
		} else {
			alert('變更已取消');
		}
	});
	$('#sendemail').on('click',function(){
		if(confirm('確認更改電子郵件地址為："'+$('#Nmail').val()+'" ?')){
			$('#emailform').submit();
		} else {
			alert('變更已取消');
		}
	});
	
	
});
/*******************************************************/
