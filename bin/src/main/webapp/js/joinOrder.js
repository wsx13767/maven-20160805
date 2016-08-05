/**-jquery cookies 套件-**/
jQuery.cookie = function(name, value, options) {
    if (typeof value != 'undefined') { // name and value given, set cookie
        options = options || {};
        if (value === null) {
            value = '';
            options = $.extend({}, options); // clone object since it's unexpected behavior if the expired property were changed
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString(); // use expires attribute, max-age is not supported by IE
        }
        // NOTE Needed to parenthesize options.path and options.domain
        // in the following expressions, otherwise they evaluate to undefined
        // in the packed version for some reason...
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else { // only name given, get cookie
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                // Does this cookie string begin with the name we want?
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};
 (function ($) {
                $.getUrlParam = function (name) {
                    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
                    var r = window.location.search.substr(1).match(reg);
                    if (r != null) return unescape(r[2]); return 1;
                }
            })(jQuery);
$(function(){
    /*var a=$.cookie("joinOrder_jsonCookie"+$.getUrlParam('order'));
    $.parseJSON(a);
    console.log(a);*/
    /*--ajax取得欄位資料--*/
    var d=new Date();
    var item_price = [];
    var item_name=[];
    var item_number=[];
    var totalMoney=0;
    var className="";
    var classIndex="";
    var optionIndex="";
    var optionIndex2="";
    var sizeArray=['b','m','s'];
    var disten=0;
    var drinkPrice=[];
    var drinkSize=[];
    var drinkIce=[];
    var drinkSuger=[];
    var thisOrder;
    var count_item;
    var isCheck=false;
    var def=$.Deferred();
    var order="order="+$.getUrlParam('order');
    $('.order_number').append('<input class="hidden" name="order_number" value="'+$.getUrlParam('order')+'" form="jform">');
    getCount();
    function getCount(){
     $.ajax({
                  url : './SendJoinOrderItemCount',
                  dataType : 'json',
                  data:order,
                  error:function(data) {
                      count=0;
                      getItemdata()
                      //alert(1);
                  },
                  success : function(data) {
                            count=parseInt(data[0].countJoinItem); 
                            getItemdata()
                       //alert(12);
                  }
              });
    }
        function getItemdata(){
            $.ajax({
                  url : './SendJsonToJoinOrderAjax',
                  dataType : 'json',
                  data:order,
                  success : function(data) {
                      console.log(data);
                      d=Date(data[0].buildOrder.deadline);//轉換日期時間格式
                      var myDate = new Date(d);
                      for(var i in data){
                          if(data[i].itemInformation.money!==undefined){
                               thisOrder="food";
                               item_name[i]=data[i].itemInformation.name;
                               item_number[i]=data[i].itemInformation.itemNumber;
                               item_price[i]=data[i].itemInformation.money;
                          }else{
                              thisOrder="drink";
                              item_name[i]=data[i].itemInformation.name;
                              item_number[i]=data[i].itemInformation.itemNumber;
                              drinkSize[i]=data[i].itemInformation.size.split("/");
                              drinkIce[i]=data[i].itemInformation.ice.split("/");
                              drinkSuger[i]=data[i].itemInformation.suger.split("/");
                              drinkPrice[parseInt(i)+disten]=parseInt(data[i].itemInformation.bigPrice);
                              drinkPrice[parseInt(i)+disten+1]=parseInt(data[i].itemInformation.midPrice);
                              drinkPrice[parseInt(i)+disten+2]=parseInt(data[i].itemInformation.smallPrice);
                              disten=disten+2;
                          }     
                      }   
                      if(data[0].itemInformation.money === undefined){
                          for(var i=0;i<drinkPrice.length;i=i+3){
                              item_price[item_price.length]=drinkPrice[i]||drinkPrice[i+1]||drinkPrice[i+2];//若大杯沒值則取中杯,依此類推
                          }
                      }

                      //console.log(drinkPrice);
                      count_item=(parseInt(data[0].itemInformation.max)-count);
                      $('.item_name').html(data[0].buildOrder.itemInformation);
                      $('.join_food_limit').append("<p class='can_qun'>"+"成團標準:&nbsp;最低數量:"+data[0].itemInformation.min+"最高數量:"+data[0].itemInformation.max+"</p>"+
                                                                          "<p>商品尚餘數量:<span id='countItem'>"+count_item+"</span></p>"+
                                                                          "<p class='join_deadline'>截止時間:"+
                                                                          myDate.getFullYear()+ "-" +myDate.getMonth() + "-" + myDate.getDate()
                                                                          +"</p>");
                      setCookies();
                      inputCount();
                      setTimeout(function(){
                        $('.j_count_item_0').trigger('change');
                        },200)
                     
                  }
            });
      }
      /*--動態顯示剩餘數量--*/
      function inputCount(){/****超蝦.attr('value')讀取錯誤,要使用.val()才會正確*/
            $('input[type=number]').on('change',function(){
                  var allCount=0;
                  var thisCount=parseInt($(this).val());
                  $('input[type=number]').each(function(){
                  allCount=allCount+parseInt($(this).val()); 
              });
               if(count_item-allCount>=0){
                        $('#countItem').html(count_item-allCount);
                  }else{
                        $(this).val(count_item+thisCount-allCount);
                        $('#countItem').html(0);
                        $(this).trigger('input');
                        alert('商品不足');
                  }
            })
      }

    /*--依照使用者輸入動態修改網頁內容--*/
    function select_input_on(k){
            $('.select_'+k).on('change',function(){
                //console.log(item_price);
                className=$(this).attr('class');
                classIndex=className.charAt(7);
                optionIndex=$('option:selected',$(this)).index();
                $('.j_price_'+k).html(item_price[optionIndex]+'元');  
                $('.hideInputPrice_'+classIndex).html('<input class="hidden"  name="j_price_'+classIndex+'" value="'+item_price[optionIndex]+'" form="jform">');
                $('.j_total_price_'+k).html(item_price[optionIndex]*$('.j_count_item_'+classIndex).val()+'元');
                $('.hideInputTPrice_'+classIndex).html('<input class="hidden"  name="j_total_price_'+classIndex+'" value="'+(item_price[optionIndex]*$('.j_count_item_'+classIndex).val())+'" form="jform">');
                $('.hideInputIndex_'+classIndex).html('<input class="hidden"  name="j_index_'+classIndex+'" value='+optionIndex+' form="jform">');
                $('.hideInputINumber_'+classIndex).html('<input class="hidden"  name="j_itemNumber_'+classIndex+'" value="'+item_number[optionIndex]+'" form="jform">');
                    var d2='';
                    var d3='';
                    var d4='';
                    if($.inArray('b',drinkSize[optionIndex])!=-1){d2='<option value="b">大杯</option>';}
                    if($.inArray('m',drinkSize[optionIndex])!=-1){d3='<option value="m">中杯</option>';}
                    if($.inArray('s',drinkSize[optionIndex])!=-1){d4='<option value="s">小杯</option>';}
                    $('.j_chose_'+k).html(d2+d3+d4);   

                    var d7='';
                    var d8='';
                    var d9='';
                    var d10='';
                    var d11='';
                    if($.inArray('10',drinkSuger[optionIndex])!=-1){d7='<option value="10">正常</option>';}
                    if($.inArray('7',drinkSuger[optionIndex])!=-1){d8='<option value="7">7分</option>';}
                    if($.inArray('5',drinkSuger[optionIndex])!=-1){d9='<option value="5">半糖</option>';}
                    if($.inArray('3',drinkSuger[optionIndex])!=-1){d10='<option value="3">3分</option>';}
                    if($.inArray('0',drinkSuger[optionIndex])!=-1){d11='<option value="0">無糖</option>';}
                    //console.log('.j_chose_suger_'+k);
                    $('.j_chose_suger_'+k).html(d7+d8+d9+d10+d11);

                    var d14='';
                    var d15='';
                    var d16='';
                    if($.inArray('10',drinkIce[optionIndex])!=-1){d14='<option value="10">正常</option>';}
                    if($.inArray('5',drinkIce[optionIndex])!=-1){d15='<option value="5">少冰</option>';}
                    if($.inArray('0',drinkIce[optionIndex])!=-1){d16='<option value="0">去冰</option>';}
                    $('.j_chose_ice_'+k).html(d14+d15+d16);

                changeTotalMoney();
            });
        if(thisOrder=="food"){
            $('.j_count_item_'+k).on('input',function(){
                className=$(this).attr('class');
                classIndex=className.charAt(13);
                var index=$('option:selected',$('.select_'+classIndex)).index();
                $('.j_total_price_'+k).html(item_price[index]*$(this).val()+'元');
                $('.hideInputTPrice_'+classIndex).html('<input class="hidden"  name="j_total_price_'+classIndex+'" value="'+(item_price[index]*$(this).val())+'" form="jform">');
                $('.hideInputIndex_'+classIndex).html('<input class="hidden"  name="j_index_'+classIndex+'" value='+index+' form="jform">');
                $('.hideInputINumber_'+classIndex).html('<input class="hidden"  name="j_itemNumber_'+classIndex+'" value="'+item_number[index]+'" form="jform">');
                changeTotalMoney();
            });
        }else{
            $('.j_count_item_'+k).on('input',function(){
                className=$(this).attr('class');
                classIndex=className.charAt(13);
                optionIndex=$('option:selected',$('.select_'+classIndex)).index();
                optionIndex2=$.inArray($('option:selected',$('.j_chose_'+classIndex)).val(),sizeArray);
                $('.j_total_price_'+k).html(drinkPrice[(3*optionIndex)+optionIndex2]*$(this).val()+'元');
                $('.hideInputTPrice_'+classIndex).html('<input class="hidden"  name="j_total_price_'+classIndex+'" value="'+(drinkPrice[(3*optionIndex)+optionIndex2]*$(this).val())+'" form="jform">');
                $('.hideInputIndex_'+classIndex).html('<input class="hidden"  name="j_index_'+classIndex+'" value='+optionIndex+' form="jform">');   
                $('.hideInputINumber_'+classIndex).html('<input class="hidden"  name="j_itemNumber_'+classIndex+'" value="'+item_number[optionIndex]+'" form="jform">');          
                changeTotalMoney();
            });
        }
    }

    function drinkSizeChange(k){
        setTimeout(function(){
                 $('.j_chose_'+k).on('change',function(){
                         className=$(this).attr('class');
                         classIndex=className.charAt(8);
                         optionIndex=$('option:selected',$('.select_'+classIndex)).index();
                         optionIndex2=$.inArray($('option:selected',$(this)).val(),sizeArray);
                         $('.j_price_'+classIndex).html(drinkPrice[(3*optionIndex)+optionIndex2]+'元');  
                         $('.hideInputPrice_'+classIndex).html('<input class="hidden"  name="j_price_'+classIndex+'" value="'+drinkPrice[(3*optionIndex)+optionIndex2]+'" form="jform">');
                         $('.j_total_price_'+classIndex).html(drinkPrice[(3*optionIndex)+optionIndex2]*$('.j_count_item_'+classIndex).val()+'元');
                         $('.hideInputTPrice_'+classIndex).html('<input class="hidden"  name="j_total_price_'+classIndex+'" value="'+(drinkPrice[(3*optionIndex)+optionIndex2]*$('.j_count_item_'+classIndex).val())+'" form="jform">');
                         changeTotalMoney();
            });
        },500); 
    }
    var moneyEnough=true;
    var changeTotalMoney=function(){
                totalMoney=0;
                if(thisOrder=="food"){
                    $('table td select').each(function(){
                        className=$(this).attr('class');
                        classIndex=className.charAt(7);
                        optionIndex=$('option:selected',$('.select_'+classIndex)).index();
                        totalMoney=totalMoney+item_price[optionIndex]*$('.j_count_item_'+classIndex).val();
                    });
                    $('.hideInputTotal_0').html('<input class="hidden"  name="j_total_money_0" value="'+totalMoney+'" form="jform">');
                    $('.join_total p').html("總金額:"+totalMoney+"元");
                    if((parseInt($('.cart a').text())-totalMoney)<0&&moneyEnough==true){alert('餘額不足');moneyEnough=false;}
                    if((parseInt($('.cart a').text())-totalMoney)>=0){moneyEnough=true;}
                }else{
                     $('table td select').each(function(){
                        className=$(this).attr('class');
                        classIndex=className.charAt(7);
                        optionIndex=$('option:selected',$('.select_'+classIndex)).index();
                        optionIndex2=$.inArray($('option:selected',$('.j_chose_'+classIndex)).val(),sizeArray);
                        totalMoney=totalMoney+drinkPrice[(3*optionIndex)+optionIndex2]*$('.j_count_item_'+classIndex).val();
                    });
                    $('.hideInputTotal_0').html('<input class="hidden"  name="j_total_money_0" value="'+totalMoney+'" form="jform">');
                    $('.join_total p').html("總金額:"+totalMoney+"元");
                    if((parseInt($('.cart a').text())-totalMoney)<0&&moneyEnough==true){alert('餘額不足');moneyEnough=false;}
                    if((parseInt($('.cart a').text())-totalMoney)>=0){moneyEnough=true;}
                }
            };
    if(thisOrder=="food"){select_input_on(0); }else{select_input_on(0); drinkSizeChange(0); }//初始化第0項
    
    /*--幫添加的select填入項目--*/
    function insertItem(joinCF){
      for(var i in item_name){
                $('.select_'+joinCF).append("<option value="+item_name[i]+">"+item_name[i]+"</option>");
            }
    }
    /*--監聽新增的項目--*/
    function hearMoney(joinCF){ 
      select_input_on(joinCF);
      $('.hideInputPrice_'+joinCF).html('<input class="hidden"  name="j_price_'+joinCF+'" value="'+item_price[0]+'" form="jform">');
      $('.hideInputTPrice_'+joinCF).html('<input class="hidden"  name="j_total_price_'+joinCF+'" value="'+item_price[0]+'" form="jform">');
      $('.hideInputIndex_'+joinCF).html('<input class="hidden"  name="j_index_'+joinCF+'" value="0" form="jform">');
      $('.hideInputINumber_'+joinCF).html('<input class="hidden"  name="j_itemNumber_'+joinCF+'" value="'+item_number[0]+'" form="jform">');
    }
    /*---增加訂購項目---*/
    var joinCF= 0;//紀錄最後一個table編號
    var add_max=8;
    function addTab(){
      //console.log("joinCF= "+joinCF);
      var deferred = $.Deferred();
        if(joinCF>=add_max){
            alert("最多8項");
            return false;
        }
    //joinCF++;
    var jTxt0 ='<table class="join_font addTab insertTab_'+joinCF+'">'+'<tr id="m_table_'+joinCF+'"'+'style="border-top:1px solid #ccc; font-size:25px; margin-top:5px">';
    var jTxt1 ='<td><input type="checkbox" name="delCheckBox" class="tableCheckBox delCheck_'+joinCF+'" form="delItem"></td>'
    var jTxt2 ='<td><select style="width:150px;height:37px;" name="j_item_'+joinCF+'"  class="select_'+joinCF+'" form="jform">';
    var jTxt3 ='</select>'
    var jTxt4 ='<span class="hideInputPrice_'+joinCF+'" form="jform"></span><span class="hideInputTPrice_'+joinCF+'" form="jform"></span><span class="hideInputIndex_'+joinCF+'" form="jform"></span><span class="hideInputINumber_'+joinCF+'" form="jform"></span></td>';
    var jTxt5 ='<td><input style="width:60px" type="number" class="j_count_item_'+joinCF+'" value="1" min="1" max="9999" form="jform" name="j_item_quan_'+joinCF+'"/></td><td><p class="j_price_'+joinCF+'">'+item_price[0]+'元</p></td><td><p  class="j_total_price_'+joinCF+'">'+item_price[0]+'元</p></td><td><button style="border:0; color:red; background:#eeede8" class="appEdBtn appEditF_'+joinCF+'" value='+joinCF+'>選項</button></td></tr></table>';
    var jTxt6 =jTxt0+jTxt1+jTxt2+jTxt3+jTxt4+jTxt5;
    $(".join_hrDiv").before(jTxt6);
    /*--調整所有新增的td寬度--*/
    var count_td=0,
        myTh=$('.m_table_top th');
     $('#m_table_'+joinCF+' td').each(function(){
        $(this).css('width',myTh.eq(count_td).width());
        count_td++;
      });
      insertItem(joinCF);
      hearMoney(joinCF);
      addEditFf(joinCF);
      inputCount();
      $('.j_count_item_0').trigger('change');
      if(thisOrder=="drink"){drinkSizeChange(joinCF);}
      changeTotalMoney();
      isCheck=false;
      joinCF++;//新增完畢後joinCF+1
      return deferred.resolve();
    }
    $('.appJoinF').click(function(){
         addTab();
    });
    /*--增加飲品訂購選項--*/
    function addEditFf(joinCF){
        if(thisOrder=="food"){
            $('.appEdBtn').attr('disabled', true);
            $('.appEdBtn').css('color', '#999');
        }else{
                var index=$('option:selected',$('.select_'+joinCF)).index();
                var jEdTxt='<div style="font-size:24px;" class="join_font joinChbDiv hide '+'ins_'+joinCF+'">'
                    var d0='<span style="margin-left:200px;">大小:</span>';
                    var d1='<select name="j_chose_'+joinCF+'" class="j_chose_'+joinCF+'" form="jform">';
                    var d2='';
                    var d3='';
                    var d4='';
                    var d5='</select>';
                    var d6='<span style="margin-left:20px;">甜度:</span><select name="j_chose_suger_'+joinCF+'" class="j_chose_suger_'+joinCF+'" form="jform">';
                    var d7='';
                    var d8='';
                    var d9='';
                    var d10='';
                    var d11='';
                    var d12='</select>';
                    var d13='<span style="margin-left:20px;">冰塊:</span><select name="j_chose_ice_'+joinCF+'" class="j_chose_ice_'+joinCF+'" form="jform">';
                    var d14='';
                    var d15='';
                    var d16='';
                    var d17='</select>';

                    if($.inArray('b',drinkSize[index])!=-1){d2='<option value="b">大杯</option>';}
                    if($.inArray('m',drinkSize[index])!=-1){d3='<option value="m">中杯</option>';}
                    if($.inArray('s',drinkSize[index])!=-1){d4='<option value="s">小杯</option>';}

                    if($.inArray('10',drinkSuger[index])!=-1){d7='<option value="10">正常</option>';}
                    if($.inArray('7',drinkSuger[index])!=-1){d8='<option value="7">7分</option>';}
                    if($.inArray('5',drinkSuger[index])!=-1){d9='<option value="5">半糖</option>';}
                    if($.inArray('3',drinkSuger[index])!=-1){d10='<option value="3">3分</option>';}
                    if($.inArray('0',drinkSuger[index])!=-1){d11='<option value="0">無糖</option>';}

                    if($.inArray('10',drinkIce[index])!=-1){d14='<option value="10">正常</option>';}
                    if($.inArray('5',drinkIce[index])!=-1){d15='<option value="5">少冰</option>';}
                    if($.inArray('0',drinkIce[index])!=-1){d16='<option value="0">去冰</option>';}

                    var d18='</div>';
                    $('.insertTab_'+joinCF).after(jEdTxt+d0+d1+d2+d3+d4+d5+d6+d7+d8+d9+d10+d11+d12+d13+d14+d15+d16+d17+d18);
                    appEditfClick(joinCF);
                      
        }        

    }
    function appEditfClick(joinCF){
            $('.appEditF_'+joinCF).on("click",function () {//新增的元素一定要ONCLICK才有用 TMD....
                        var a=$(this).val();
                        $('.ins_'+a).toggleClass('hide');
                    });
         }
    /*--顯示文字區塊--*/ 
    $(document).on("click",'.jps', function () {
        if( $('.join_hrDiv textarea').hasClass('hide')){
        $('.join_hrDiv textarea').removeClass('hide').stop(true).animate({
            height:'150px'
        });
       }else{$('.join_hrDiv textarea').css('height','0px').addClass('hide')}
    });
    /*--cookie處理--*/
   function setCookies(){
      if($.cookie("joinOrder_jsonCookie_"+$.getUrlParam('order')+"_"+$('input[name=user_id]').val().trim())!=null){
            var myCookie=$.cookie("joinOrder_jsonCookie_"+$.getUrlParam('order')+"_"+$('input[name=user_id]').val().trim());
            //console.log("joinOrder_jsonCookie_"+$.getUrlParam('order')+"_"+$('input[name=user_id]').attr('value').trim());
            var json=$.parseJSON(myCookie);
            var jsonObj=JSON.parse(json);
            if(myCookie.length==4){addTab();delClick(); return false}//若myCookie="[]"的話就新增一個預設的table
            if(jsonObj.length==0){addTab();delClick(); return false}
            setTimeout(function(){
                for(var i in jsonObj){
                    $.when(addTab()).done(function(){
                         $('.select_'+i+' option:eq('+jsonObj[i].jIndex+')').prop('selected', true);
                         $('.select_'+i).trigger("change");
                         if(thisOrder=="drink"){
                                //console.log("i= "+i);
                                $('.j_chose_'+i+' option:eq('+$.inArray(jsonObj[i].jSize,drinkSize[jsonObj[i].jIndex])+')').prop('selected', true);
                                $('.j_chose_suger_'+i+' option:eq('+(drinkSuger[jsonObj[i].jIndex].length-$.inArray(jsonObj[i].jSuger,drinkSuger[jsonObj[i].jIndex])-1)+')').prop('selected', true);
                                $('.j_chose_ice_'+i+' option:eq('+(drinkIce[jsonObj[i].jIndex].length-$.inArray(jsonObj[i].jIce,drinkIce[jsonObj[i].jIndex])-1)+')').prop('selected', true);
                                $('.j_chose_'+i+' option:eq('+$.inArray(jsonObj[i].jSize,drinkSize[jsonObj[i].jIndex])+')').prop('selected', true);//這句可能執行太快,執行2次就不會有BUG
                          }
                          $('.j_count_item_'+i).val(jsonObj[i].jItemQuan);
                          optionIndex=$('option:selected',$(this)).index();
                          $('.j_price_'+i).html(jsonObj[i].jPrice+'元');  
                          $('.hideInputPrice_'+i).html('<input class="hidden"  name="j_price_'+i+'" value="'+jsonObj[i].jPrice+'" form="jform">');
                          $('.j_total_price_'+i).html(jsonObj[i].jPrice*$('.j_count_item_'+i).val()+'元');
                          $('.hideInputTPrice_'+i).html('<input class="hidden"  name="j_total_price_'+i+'" value="'+jsonObj[i].jPrice*$('.j_count_item_'+i).val()+'" form="jform">');
                          $('.hideInputIndex_'+i).html('<input class="hidden"  name="j_index_'+i+'" value='+jsonObj[i].jIndex+' form="jform">');
                          changeTotalMoney();     
                    });                   
                }
            },50)/***設定時間非常重要!!!!***/
       }else{addTab()}
    };
    
     /*--刪除選項--*/
     delClick();
    function delClick(){
            $('#join_btn3').on('click',function(){
            var countTab=0;
             $("input[name='delCheckBox']").each(function() {
                className=$(this).attr('class');
                classIndex=className.charAt(23);
                if($(this).prop("checked")){
                    $('.ins_'+classIndex).children().remove();
                    $('.ins_'+classIndex).remove();
                    $('.insertTab_'+classIndex).remove();
                    joinCF--;
                    //console.log("joinCF= "+joinCF);
                }
                set();
             });
         })
    }
         /*--刪除後調整各項參數--*/
    function set(){
        var reCount=0;
         $('table td select').each(function(){
                    className=$(this).attr('class');
                    classIndex=className.charAt(7);
                    $('.select_'+classIndex).unbind('change');
                    $('.j_count_item_'+classIndex).unbind('input');
                    $('.appEditF_'+classIndex).unbind('click');//解除之前的綁定
                    $('.j_chose_'+classIndex).attr('name','j_chose_'+reCount);
                    $('.j_chose_'+classIndex).attr('class','j_chose_'+reCount);
                    $('.j_chose_suger_'+classIndex).attr('name','j_chose_suger_'+reCount);
                    $('.j_chose_suger_'+classIndex).attr('class','j_chose_suger_'+reCount);
                    $('.j_chose_ice_'+classIndex).attr('name','j_chose_ice_'+reCount);
                    $('.j_chose_ice_'+classIndex).attr('class','j_chose_ice_'+reCount);
                    $('.ins_'+classIndex).attr('class','join_font joinChbDiv hide ins_'+reCount);
                    $(".insertTab_"+classIndex+" button[value='"+reCount+"']").val(reCount);
                    $('.j_total_price_'+classIndex).attr('class','j_total_price_'+reCount);
                    $('.j_price_'+classIndex).attr('class','j_price_'+reCount);
                    $('.j_count_item_'+classIndex).attr('name','j_item_quan_'+reCount);
                    $('.j_count_item_'+classIndex).attr('class','j_count_item_'+reCount);
                    $('.hideInputIndex_'+classIndex+' input').attr('name','j_index_'+reCount);
                    $('.hideInputIndex_'+classIndex).attr('class','hideInputIndex_'+reCount);
                    $('.hideInputTPrice_'+classIndex+' input').attr('name','j_total_price_'+reCount);
                    $('.hideInputTPrice_'+classIndex).attr('class','hideInputTPrice_'+reCount);
                    $('.hideInputPrice_'+classIndex+' input').attr('name','j_price_'+reCount);
                    $('.hideInputPrice_'+classIndex).attr('class','hideInputPrice_'+reCount);
                    $('.hideInputINumber_'+classIndex+' input').attr('name','j_itemNumber_'+reCount);
                    $('.hideInputINumber_'+classIndex).attr('class','hideInputINumber_'+reCount);
                    $('.appEditF_'+classIndex).val(reCount)
                    $('.appEditF_'+classIndex).attr('class','appEdBtn appEditF_'+reCount);
                    $('.select_'+classIndex).attr('name','j_item_'+reCount);
                    $('.select_'+classIndex).attr('class','select_'+reCount);
                    $('.delCheck_'+classIndex).attr('class','tableCheckBox delCheck_'+reCount);
                    $('#m_table_'+classIndex).attr('id','m_table_'+reCount);
                    $('.insertTab_'+classIndex).attr('class','join_font addTab insertTab_'+reCount);
                    $("delCheck_"+classIndex).attr('class','tableCheckBox delCheck_'+reCount);
                    //調整監聽目標
                    select_input_on(reCount);
                    appEditfClick(reCount);
                    inputCount();
                    $('.j_count_item_0').trigger('change');
                    reCount++;
                });
         changeTotalMoney();
    }

    /*--找出重複項目--*/
    var reItem=[];
    var reIndex=[];
    var reNumber=[];
    var delIndex=[];
   function reItemTest(){
       $('table td select').each(function(){
            //alert('re');
            className=$(this).attr('class');
            classIndex=className.charAt(7);
            optionIndex=$('option:selected',$('.select_'+classIndex)).index();
            var sizeSel=$('option:selected',$('.j_chose_'+classIndex)).val();
            var sugerSel=$('option:selected',$('.j_chose_suger_'+classIndex)).val();
            var iceSel=$('option:selected',$('.j_chose_ice_'+classIndex)).val();
            var re=item_name[optionIndex]+'/'+sizeSel+'/'+sugerSel+'/'+iceSel;
            //console.log(re);
            if($.inArray(re,reItem)!=-1){
                  var num=parseInt($.inArray(re,reItem));//與第幾項重複
                  reIndex[reIndex.length]=num;
                  if(delIndex[num]===undefined){delIndex[num]=''}
                  delIndex[num]=delIndex[num]+'/'+classIndex;//須被合併的項目
                  //console.log('delIndex= '+delIndex);
                  if(reNumber[num]===undefined){reNumber[num]=0}
                  reNumber[num]=reNumber[num]+parseInt($('.j_count_item_'+classIndex).val());//合併時要增加的數量
                  reItem.push('none');
            }else{
                  reItem.push(re);
            }
      });
   }
   /*--點擊送出時--*/
   $('#join_btn2').on('click',function(){
      reItem=[];
      reNumber=[];
      reIndex=[];
      delIndex=[];
      reItemTest();
    if(moneyEnough==true){
        if(reNumber.length==0||isCheck==true){
              $('#jform').submit();
        }else{
              $('.confirm').html('<h1>訂單項目重複</h1><p>您勾選的項目將被合併</p><table id="merge"></table><button >取消</button><!-- autofocus --><button >合併</button>');
              reIndex = Array.from(new Set(reIndex));//移除重複元素
              for(var i in reIndex){
                    //console.log('reitem= '+reItem);
                    var k=reItem[reIndex[i]].split('/');
                    if(thisOrder=='drink'){
                          switch (k[1]){
                                case 'b':k[1]='大杯';break;
                                case 'm':k[1]='中杯';break;
                                case 's':k[1]='小杯';break;
                          }
                          switch (k[2]){
                                case '10':k[2]='正常';break;
                                case '7':k[2]='7分';break;
                                case '5':k[2]='半糖';break;
                                case '3':k[2]='3分';break;
                                case '0':k[2]='無糖';break;
                          }
                          switch (k[3]){
                                case '10':k[3]='正常';break;
                                case '5':k[3]='少冰';break;
                                case '0':k[3]='去冰';break;
                          }
                    }
                    //console.log("k= "+k);
                    var a='<tr><td><input type="checkbox" value='+reIndex[i]+'></td>';
                    var b='<td>'+k[0]+'</td>';
                    var c='<td>'+k[1]+'</td>';
                    var d='<td>'+k[2]+'</td>';
                    var e='<td>'+k[3]+'</td></tr>';                                                          
                    if(thisOrder=='drink'){
                          $('#merge').append(a+b+c+d+e);
                    }else{
                          $('#merge').append(a+b);
                    }
              }
              $('.bg').css({'display':'block'});
              $('.content').css({'display':'block'});
              selMerge();
        }
        isCheck=true;
      }else{alert('餘額不足');}
   });

   /*--選擇合併--*/
  function selMerge(){
       $('.confirm button').eq(1).on('click',function(){
            $('.bg').trigger('click');
            var a=[];
            $('.confirm input[type=checkbox]').each(function(){
                        if($(this).prop('checked')){
                             // console.log("a..."+parseInt($(this).attr('value')));
                              a.push(parseInt($(this).val()));
                        }else{
                              delete delIndex[parseInt($(this).val())];
                        }
                  });
            merge(a); 
         });
       $('.confirm button').eq(0).on('click',function(){
           $('.bg').trigger('click');
      });     
  }


   /*--項目合併--*/
   function merge(a){
      //reIndex = Array.from(new Set(reIndex));//移除重複元素
      //console.log("reNumber= "+reNumber); 
            for(var i in delIndex){
                  var spl=delIndex[i].split('/');
                  for(var i in spl){
                        if(i>0){
                              //console.log('spl['+i+']= '+spl[i]);
                              $("input[name='delCheckBox']").eq(spl[i]).prop('checked',true);
                        }   
                  }    
            }
            $('#join_btn3').trigger('click');
            for(var i in a){
                  //console.log(reNumber);
                  //console.log(reNumber[a[i]]);
                  $('.j_count_item_'+a[i]).val(parseInt($('.j_count_item_'+a[i]).val())+reNumber[a[i]]);
                  $('.j_count_item_'+a[i]).trigger('input');
            }       
            $('input[type=number]').trigger('change');  
   }
   /*-遮罩控制-*/
      $('.bg').click(function(){
                  $('.bg').fadeOut(100);
                  $('.content').fadeOut(100);
      });
});
