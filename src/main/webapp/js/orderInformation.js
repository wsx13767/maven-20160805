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
    var sizeArray=['b','m','s'];
    var thisOrder;
    var count=0;
    var order="order="+$.getUrlParam('order');
    $('.order_number').append('<input class="hidden" name="order_number" value="'+$.getUrlParam('order')+'" form="jform">');
    $.ajax({
            url : './SendJoinOrderItemCount',
            dataType : 'json',
            data:order,
            error:function(data) {
                count=0;
            },
            success : function(data) {
                count=parseInt(data[0].countJoinItem); 
            }
        }).done(getItemdata());

  function getItemdata(){
     return $.ajax({
                  url : './OrderInformation',
                  dataType : 'json',
                  data:order,
                  success : function(data) {
                      console.log(data);
                      $('.etalage_small_thumbs').addClass('hide');
                      $('.etalage_small_thumbs').css('width','0px','height','0px');
                      $('#order_name').html(data[0].buildOrder.itemInformation);
                      $('#item_remark').html(data[0].buildOrder.remark);
                      $('#goal_max').html(data[0].itemInformation.max);
                      $('#goal_min').html(data[0].itemInformation.min);
                      $('#build_time').html("發起時間: "+data[0].buildOrder.buildTimeString);
                      $('#dead_time').html("截止時間: "+data[0].buildOrder.deadlineString);
                      $('.etalage_thumb_image').load().attr('src','images/'+data[0].buildOrder.picture);
                      $('.etalage_source_image').load().attr('src','images/'+data[0].buildOrder.picture);
                      if(data[0].itemInformation.money!==undefined){
                      $('#goal_max').html("團購最大目標: "+data[0].itemInformation.max+"份");
                      $('#goal_min').html("團購最小目標: "+data[0].itemInformation.min+"份");
                      $('#goal_r').html("尚餘數量: "+(parseInt(data[0].itemInformation.max)-count)+"份");
                      $('#goal_c').html("已賣數量: "+count+"份");
                      $('#input_information').html('<table id="drink_information"><tr><th></th><th></th><th></th></tr></table>');
                    }else{
                      $('#goal_max').html("團購最大目標: "+data[0].itemInformation.max+"杯");
                      $('#goal_min').html("團購最小目標: "+data[0].itemInformation.min+"杯");
                      $('#goal_r').html("尚餘數量: "+(parseInt(data[0].itemInformation.max)-count)+"份");
                      $('#goal_c').html("已賣數量: "+count+"份");
                      $('#input_information').html('<table id="drink_information"><tr><th></th><th></th><th>L</th><th>M</th><th>S</th></tr></table>');
                    }
                      for(var i in data){
                          if(data[i].itemInformation.money!==undefined){
                                $('#drink_information').append('<tr><td>'+(parseInt(i)+1)+'.</td><td>'+data[i].itemInformation.name+'</td><td>'+data[i].itemInformation.money+'元</td></tr>');
                          }else{
                              if(data[i].itemInformation.bigPrice===undefined){var dib='';}else{var dib=data[i].itemInformation.bigPrice+'元'}
                              if(data[i].itemInformation.midPrice===undefined){var dim='';}else{var dim=data[i].itemInformation.midPrice+'元'}
                              if(data[i].itemInformation.smallPrice===undefined){var dis='';}else{var dis=data[i].itemInformation.smallPrice+'元'}
                              $('#drink_information').append('<tr><td>'+(parseInt(i)+1)+'.</td><td>'+data[i].itemInformation.name+'</td><td>'+dib+'</td><td>'+dim+'</td><td>'+dis+'</td></tr>');
                          }     
                      } 

                      $('#sinBtn').on('click',function(){
                        location.href='/G/joinOrder.jsp?order='+$.getUrlParam('order');
                      });
                      $('.images_3_of_2 a').attr('href','/G/joinOrder.jsp?order='+$.getUrlParam('order'));

                     /* $('.join_food_limit').append("<p class='can_qun'>"+"成團標準:&nbsp;最低數量:"+data[0].itemInformation.min+"最高數量:"+data[0].itemInformation.max+"</p>"+
                                                                          "<p>商品尚餘數量:"+(parseInt(data[0].itemInformation.max)-count)+"</p>"+
                                                                          "<p class='join_deadline'>截止時間:"+
                                                                          myDate.getFullYear()+ "-" +myDate.getMonth() + "-" + myDate.getDate() + "  " +myDate.getHours()+":"+ myDate.getMinutes() 
                                                                          +"</p>");*/
                     
                   }
            });
    }

  });