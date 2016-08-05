$(function() {
        (function ($) {
            $.getUrlParam = function (name) {
                var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                var r = window.location.search.substr(1).match(reg);
                if (r != null) return unescape(r[2]); return "DATE";
            }
        })(jQuery);
        

        /**-判斷是哪種查詢-**/
        var orderBy=$.getUrlParam('sortBy');
        var sort="ASC";
        var category=$.getUrlParam('category');
        var limit=$.getUrlParam('limit');
        var start=$.getUrlParam('start');
        var oderIs="";
        if(category=="FOOD"){$('select[name=forma] option').prop('selected',2);changeCategory();}else if(category=="DRINK"){$('select[name=forma] option').eq(1).prop('selected',true);changeCategory();}else{$('select[name=forma] option').prop('selected',0);changeCategory();}
        if(limit=="30"){$('select[name=limit] option').prop('selected',2);changeLimit();}else if(limit=="20"){$('select[name=limit] option').eq(1).prop('selected',true);changeLimit();}else{$('select[name=limit] option').prop('selected',0);changeLimit();}
        var s="?";


        if(category!="FOOD"&&category!="DRINK"){
          category="ALL";
         }

        if(limit!="10"&&limit!="20"&&limit!="30"){
          limit="10"; 
         }

        if(!isNaN(parseInt(start))&&parseInt(start)%10!=0&&parseInt(start)%20!=0&&parseInt(start)%30!=0){
          start="0";
        }else if(isNaN(parseInt(start))){
          start="0";
        }

        if(orderBy=="DATE"){
          oderIs='odD';
          if($.getUrlParam('odD')=="ASC"){
              sort="ASC";
              $('.buyDate').attr("href", "/G/product.jsp?sortBy=DATE&odD=DESC"+"&category="+category+"&limit="+limit+"&start="+start);
          }
          else{
              sort="DESC";
              $('.buyDate').attr("href", "/G/product.jsp?sortBy=DATE&odD=ASC"+"&category="+category+"&limit="+limit+"&start="+start);
          }
          sortByMD(orderBy,sort,category,limit,start);
        }else if(orderBy=="MONEY"){
          oderIs='odM';
          if($.getUrlParam('odM')=="ASC"){
              sort="ASC";
              $('.buyMoney').attr("href", "/G/product.jsp?sortBy=MONEY&odM=DESC"+"&category="+category+"&limit="+limit+"&start="+start);
          }
          else{
              sort="DESC";
              $('.buyMoney').attr("href", "/G/product.jsp?sortBy=MONEY&odM=ASC"+"&category="+category+"&limit="+limit+"&start="+start);
          }
          sortByMD(orderBy,sort,category,limit,start);
        }else if(orderBy=="COUNT"){
          oderIs='odC';
          if($.getUrlParam('odC')=="ASC"){
              sort="ASC";
              $('.buyCount').attr("href", "/G/product.jsp?sortBy=COUNT&odC=DESC"+"&category="+category+"&limit="+limit+"&start="+start);
          }
          else{
              sort="DESC";
              $('.buyCount').attr("href", "/G/product.jsp?sortBy=COUNT&odC=ASC"+"&category="+category+"&limit="+limit+"&start="+start);
          }
          sortByCount(orderBy,sort,category,limit,start);
        }else{
          oderIs='odD';
          orderBy="DATE";
          sort="ASC";
          sortByMD(orderBy,sort,category,limit,start);
        }

        s=s+"sortBy="+orderBy+"&="+sort+"&category="+category+"&limit="+limit+"&start="+start;
        console.log("s= "+s);

        function changeCategory(){
            $('select[name=forma]').on('change',function(){
            var startCa=0;
            var index=$('select[name=forma] option:selected').index();
            if(index==0){
              category="ALL";
            }else if(index==1){
              category="DRINK";
            }else if(index==2){
              category="FOOD";
            }else{
              category="ALL";
            }
            location.href='product.jsp?sortBy='+orderBy+'&'+oderIs+'='+sort+'&category='+category+'&limit='+limit+'&start='+startCa;
          });
        }

         function changeLimit(){
            var startLimit=0;
            $('select[name=limit]').on('change',function(){
            var index=$('select[name=limit] option:selected').index();
            if(index==0){
              limit="10";
            }else if(index==1){
              limit="20";
            }else if(index==2){
              limit="30";
            }else{
              limit="10";
            }
            location.href='product.jsp?sortBy='+orderBy+'&'+oderIs+'='+sort+'&category='+category+'&limit='+limit+'&start='+startLimit;
          });
        }



        function addList(a,b,c,d,e,f,g,h,od,i,j){
            var a1 = '<div class="item_list_block"><div class="item_photo_block">', 
                            a2 = '<img src="images/'+j+'" style="width: 100%;height: 100%;" alt=""></div>', 
                            a3 = '<div class="item_text_block"><h3 class="item_h3">'
                                    + a
                                    + '</h3>', 
                            a4 = '<table class="products_table"><tr>', 
                            a5 = '<td class="item_text_1"><span class="item_price_">'
                                    + b
                                    + '~'
                                    + c
                                    + '元</span></td>', 
                            a6 = '<td class="item_text_2"><a href="./single.jsp?order='+h+'"><span>詳細</span></a></td>', 
                            a7 = '<td class="item_text_3"><span>'+d+'/'+e+'</span></td>', 
                            a8 = '<td class="item_text_4">'+i+'</td>', 
                            a9 = '</tr><tr><td class="item_text_5"><span>發起人:'+f+'</span></td><td></td><td></td><td></td></tr><tr>',
                            a10 = '<td class="item_text_6"><span>結束時間:'+g+'</span></td>',
                            a11 = '<td></td><td></td><td class="item_text_7"><a href="./joinOrder.jsp?order='+h+'"><span>加入團購</div></div>';
                                    if($.getUrlParam('odC')=="DESC"){
                                        $('.addPTab').prepend(a1 + a2 + a3 + a4 + a5 + a6+ a7 + a8 + a9 + a10+ a11);
                                  }else{
                                        $('.addPTab').append(a1 + a2 + a3 + a4 + a5 + a6+ a7 + a8 + a9 + a10+ a11);
                                  }   
        }
        


        //宣告一個二微陣列
        function Array2DVar(x,y) {  // 定義二維陣列原型
          this.length = x;
          this.x = x;  // x 維度長度
          this.y = y;  // y 維度長度
          for(var i = 0; i < this.length; i++)  // 初始各元素值為 null
          this[i] = new Array(y);  // this 代表物件本身
        }
        /*--以日期或金錢排序--*/
        function sortByMD(sortBy,od,category,limit,start){
            var price = new Array2DVar(30, 30);  // 建立新的 10*10 的二維陣列
            var re=[];//已取出的定單的訂單編號將會填在此陣列
            var count=[];//訂單有多少商品  
            var drinkPrice=new Array2DVar(30, 30);
            var allDrinkPrice=[];
            var countItemQuan=[];//計算商品數量
            $.ajax({
            url : './SendJsonToPruductAjax',
            data:"sortBy="+sortBy+"&sort="+sort+"&category="+category+"&limit="+limit+"&start="+start,
            dataType : 'json',
            success : function(data) {
                //$('.addPTab').html("")//清空原本的東西
                console.log(data);
                    for(var i in data){
                            //存入金錢
                            if(data[i].itemInformation.money!==undefined){//先判斷是食品還是飲品
                                var aparse=parseInt(data[i].buildOrder.orderNumber);
                                if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                price[aparse][count[aparse]]=data[i].itemInformation.money;//為編號為data[i].buildOrder.orderNumber的訂單的第count個值設定金錢
                                count[aparse]++;//這張訂單商品+1
                            }else{
                                var aparse=parseInt(data[i].buildOrder.orderNumber);
                                if(data[i].itemInformation.bigPrice!==undefined){
                                    if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                    drinkPrice[aparse][count[aparse]]=parseInt(data[i].itemInformation.bigPrice);
                                    count[aparse]++;//這張訂單商品+1
                                }
                                if(data[i].itemInformation.midPrice!==undefined){
                                    if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                    drinkPrice[aparse][count[aparse]]=parseInt(data[i].itemInformation.midPrice);
                                    count[aparse]++;//這張訂單商品+1
                                }
                                if(data[i].itemInformation.smallPrice!==undefined){
                                    if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                    drinkPrice[aparse][count[aparse]]=parseInt(data[i].itemInformation.smallPrice);
                                    count[aparse]++;//這張訂單商品+1
                                }
                               // console.log("drinkPrice["+aparse+"]["+count[aparse]+"]= "+drinkPrice[aparse][k]);
                            }
                            //存入數量
                            if(data[i].count===undefined){data[i].count=0;}//若遇到沒就給0(有些訂單建立沒人購買過)
                            if(countItemQuan[aparse]===undefined){countItemQuan[aparse]=0;}//若是沒有值,這邊先給0下一步才不會出現NAN
                            countItemQuan[aparse]=countItemQuan[aparse]+parseInt(data[i].count);
                            //console.log("前面的:countItemQuan["+aparse+"]= "+countItemQuan[aparse]);
                    }
                    for(var i in data){//依照當初SQL查詢填入的順序,把東西PRINT出來
                        var bparse=parseInt(data[i].buildOrder.orderNumber);
                        //console.log(bparse);
                        if($.inArray(bparse,re)==-1){
                            if(data[i].itemInformation.money!==undefined){
                                for(var k=0;k<price[bparse].length;k++){//取出max min price
                                    if(k==0){
                                        maxPrice2=price[bparse][k];
                                        minPrice2=price[bparse][k];
                                    }else if(price[bparse][k]!=null){
                                        if(price[bparse][k]>maxPrice2){
                                            maxPrice2=price[bparse][k];
                                            //console.log("maxPrice2= "+maxPrice2);
                                        }
                                        if(price[bparse][k]<minPrice2){
                                            minPrice2=price[bparse][k];
                                            //console.log("minPrice2= "+minPrice2);
                                        }
                                    }   
                                }
                            }else{//取出訂單編號為bparse的drinkPrice的值
                                allDrinkPrice=[];
                                for(var k=0;k<drinkPrice[bparse].length;k++){
                                    if(drinkPrice[bparse][k]!==undefined){
                                        allDrinkPrice.push(drinkPrice[bparse][k]);
                                       // console.log("drinkPrice["+bparse+"]["+k+"]= "+drinkPrice[bparse][k]);
                                    }             
                                } 
                                maxPrice2=Math.max(...allDrinkPrice);
                                minPrice2=Math.min(...allDrinkPrice);
                                //console.log("訂單"+bparse+"maxPrice2= "+maxPrice2);
                                //console.log("訂單"+bparse+"minPrice2= "+minPrice2);
                            }
                            //console.log("countItemQuan["+bparse+"]= "+countItemQuan[bparse]);
                            addList(data[i].buildOrder.itemInformation,minPrice2,maxPrice2,countItemQuan[bparse],data[i].itemInformation.max,data[i].userData.account,data[i].buildOrder.deadline,data[i].buildOrder.orderNumber,od,data[i].buildOrder.buildTime,data[i].buildOrder.picture);
                            re.push(bparse);
                            //console.log("迴圈執行第 "+i+"次 RE= "+re);
                        }
                        
                    }
                    totalOrder();
                }
            });
        }


        /*--以數量排序--*/
        function sortByCount(sortBy,od,category,limit,start){
            var price = new Array2DVar(30, 30);  // 建立新的 10*10 的二維陣列
            var re=[];//已取出的定單的訂單編號將會填在此陣列
            var count=[];//訂單有多少商品  
            var maxPrice2;
            var minPrice2;
            var drinkPrice=new Array2DVar(30, 30);
            var allDrinkPrice=[];
            var countItemQuan=[];//同一張訂單的第幾個價格
            var orderNUM=[];//紀錄有幾種訂單編號
            var iInOrderNum=[]//記錄訂單編號對應到第幾個i
            var countOdNumLength=0;
            $.ajax({
            url : './SendJsonToPruductAjax',
            data:"sortBy="+sortBy+"&sort="+sort+"&category="+category+"&limit="+limit+"&start="+start,
            dataType : 'json',
            success : function(data) {
                //$('.addPTab').html("")//清空原本的東西
                //console.log(data);
                    for(var i in data){
                        if(data[i].itemInformation.money!==undefined){//先判斷是食品還是飲品
                                //存入金錢
                                var aparse=parseInt(data[i].buildOrder.orderNumber);
                                if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                price[aparse][count[aparse]]=data[i].itemInformation.money;//為編號為data[i].buildOrder.orderNumber的訂單的第count個值設定金錢
                                count[aparse]++;//這張訂單商品+1
                        }else{
                                var aparse=parseInt(data[i].buildOrder.orderNumber);
                                if(data[i].itemInformation.bigPrice!==undefined){
                                    if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                    drinkPrice[aparse][count[aparse]]=parseInt(data[i].itemInformation.bigPrice);
                                    count[aparse]++;//這張訂單商品+1
                                }
                                if(data[i].itemInformation.midPrice!==undefined){
                                    if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                    drinkPrice[aparse][count[aparse]]=parseInt(data[i].itemInformation.midPrice);
                                    count[aparse]++;//這張訂單商品+1
                                }
                                if(data[i].itemInformation.smallPrice!==undefined){
                                    if(count[aparse]==null){count[aparse]=0;}//給count初始值
                                    drinkPrice[aparse][count[aparse]]=parseInt(data[i].itemInformation.smallPrice);
                                    count[aparse]++;//這張訂單商品+1
                                }
                                //console.log("drinkPrice["+aparse+"]["+count[aparse]+"]= "+drinkPrice[aparse][k]);
                            }
                        //存入數量
                        if(data[i].count===undefined){data[i].count=0;}//若遇到沒就給0(有些訂單建立沒人購買過)
                        if(countItemQuan[aparse]===undefined){countItemQuan[aparse]=0;}//若是沒有值,這邊先給0下一步才不會出現NAN
                        countItemQuan[aparse]=countItemQuan[aparse]+parseInt(data[i].count);
                        //console.log("前面的:countItemQuan["+aparse+"]= "+countItemQuan[aparse]);

                        //存入訂單編號
                        if($.inArray(aparse,orderNUM)==-1){
                            orderNUM[countOdNumLength]=aparse;
                            iInOrderNum[aparse]=i;//只要在裡面放訂單編號就可知道對應到哪個i
                            countOdNumLength++;
                        }
                    }
                    var orderSortTemp=0;
                    for(var i=orderNUM.length;i>0;i--){//因為當初是ORDERNUMBER排序,所以數量相同新的會在前面

                        for(var j=0;j<i-1;j++){
                            if(countItemQuan[orderNUM[j]]>countItemQuan[orderNUM[j+1]]){ //由小到大
                                orderSortTemp=orderNUM[j];
                                orderNUM[j]=orderNUM[j+1];
                                orderNUM[j+1]=orderSortTemp;//所以orderNUM[0]對應到最小的count              
                            }
                        }
                    }
                    //console.log("countItemQuan= "+countItemQuan);   
                    //console.log("orderNUM= "+orderNUM);
                    //console.log("iInOrderNum ="+iInOrderNum);
                    for(var i=0;i<orderNUM.length;i++){//依照數量,把東西PRINT出來
                        var dataI=iInOrderNum[orderNUM[i]]//這是某種反函數嗎XD
                        if(data[dataI].itemInformation.money!==undefined){
                                for(var k=0;k<price[orderNUM[i]].length;k++){//取出max min price
                                    if(k==0){
                                        maxPrice2=price[orderNUM[i]][k];
                                        minPrice2=price[orderNUM[i]][k];
                                    }else if(price[orderNUM[i]][k]!=null){
                                        if(price[orderNUM[i]][k]>maxPrice2){
                                            maxPrice2=price[orderNUM[i]][k];        
                                        }
                                        if(price[orderNUM[i]][k]<minPrice2){
                                            minPrice2=price[orderNUM[i]][k];
                                        }
                                    }   
                                }
                           }else{//取出訂單編號為orderNUM[i]的drinkPrice的值
                            allDrinkPrice=[];
                                for(var k=0;k<drinkPrice[orderNUM[i]].length;k++){
                                    if(drinkPrice[orderNUM[i]][k]!==undefined){
                                        allDrinkPrice.push(drinkPrice[orderNUM[i]][k]);
                                        console.log("drinkPrice["+orderNUM[i]+"]["+k+"]= "+drinkPrice[orderNUM[i]][k]);
                                    }             
                                } 
                                maxPrice2=Math.max(...allDrinkPrice);
                                minPrice2=Math.min(...allDrinkPrice);
                            }                                                                                                                                                             //最大數量裡面是放訂單編號
                        addList(data[dataI].buildOrder.itemInformation,minPrice2,maxPrice2,countItemQuan[orderNUM[i]],data[dataI].itemInformation.max,data[dataI].userData.account,data[dataI].buildOrder.deadline,data[dataI].buildOrder.orderNumber,od,data[dataI].buildOrder.buildTime,data[dataI].buildOrder.picture);
                        //console.log("迴圈執行第 "+i+"次 RE= "+re);
                    }
                    totalOrder();
                }
            });
        }

        /*--團購訂單總數--*/

        function totalOrder(){
          $.ajax({
            url : './SendTotalOrderAjax',
            data:"category="+category,
            dataType : 'json',
            success:function(data){
                //console.log(data);
                var hre='product.jsp?sortBy='+orderBy+'&'+oderIs+'='+sort+'&category='+category+'&limit='+limit+'&start=';
                var page=Math.ceil(data[0].orderCount/limit);
                $('.page-info-num').html('每頁 '+limit+' 筆，');
                $('.page-info-pagenum').html('第'+((start/limit)+1)+'/'+page+'頁');
                $('.page-info-total').html('共'+data[0].orderCount+'個團購');
                $('.page-to').eq(start/limit).addClass('active_page');
                var startPage=0;
                for(var i=0;i<page;i++){
                  startPage=i*limit;
                  $('.item_prev_page').append('<li style="margin-left:5px;" class="page-to"><a href='+hre+startPage+'>'+(parseInt(i)+1)+'</a></li>');
                }
                $('.page-to').eq(start/limit).addClass('active_page');
                if((start/limit)-1>=0){
                  $('.item_prev_page a').eq(0).attr('href',hre+((start/limit)-1)*limit);
                }else{
                  $('.item_prev_page a').eq(0).attr('href',hre+0);
                }
                if((start/limit+1)<=page*limit){
                  $('.item_next_page a').attr('href',hre+((start/limit)+1)*limit);
                }else{
                  $('.item_next_page a').attr('href',hre+page);
                }
            }
          });

        }
});