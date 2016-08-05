$(function(){
      setInterval(function(){
            getPptItem();
      },50000);
      getPptItem();
      function getPptItem(){
            $.ajax({
            url : './SendJsonToIndexPpt',
            dataType : 'json',
            success : function(data){
                 //console.log(data);
                 for(var i in data){
                        $('.powerpoint_right ul li span:odd').eq(i).html(data[i].itemInformation);
                        $('.powerpoint_right ul li a').eq(i).attr('href','/G/single.jsp?order='+parseInt(data[i].orderNumber));
                        $('.powerpoint_big_pic ul li img').eq(i).attr('src','images/'+data[i].picture);
                 }
            }
        });
       }      
       getIndexItem(1);
       var loading=false;
       var limit=false;
       function getIndexItem(row){   
            $.ajax({
            url : './SendIndexItem',
            dataType : 'json',
            data:"row="+row,
            success : function(data){
                  //console.log(loading);
                             //console.log(data);
                             for(var i in data){
                                    var posi='sed-left-top';
                                    if(i%2==0){posi=''}
                                    addItemBlock(data[i].itemInformation,data[i].orderNumber,data[i].remark,posi,data[i].picture);
                       }
                       loading=false;
                     //  $('#loadingBar').css('display','none');
                       $('#rowval').val(parseInt($('#rowval').val())+2);

                  }
            });
      }

      function addItemBlock(itemInformation,orderNumber,remark,posi,picture){
            var iText0='<a href="single.jsp?order='+orderNumber+'">';
            var iText1='<div class="col-md-6 con-sed-grid hover-sed-grid '+posi+'">';
            var iText2='<div class=" elit-grid">';
            var iText3='<h3>'+itemInformation+'</h3><hr>';
            var iText4='<p class="index_item_p" style"width:120%">'+remark+'</p>';                 
            var iText5='</div><div><img class="img-responsive index_item_img shoe-left" src="images/'+picture+'" style="width: 353.03px; height: 234.45px;" alt=" " />';
            //var iText6='<span class="on-get">GET NOW</span></div>';                 
            var iText7='<div class="clearfix"> </div></div></a>';                        
            $('.shoes-grid-left').append(iText0+iText1+iText2+iText3+iText4+iText5+iText7);
      }    
       $(window).scroll(function(){
            if((($(window).scrollTop()+$(window).height())+250)>=$(document).height()){
                  if(loading==false){
                        loading=true;
                       // $('#loadingBar').css('display','block');
                        getIndexItem($('#rowval').val());
                  }
            }
       });
});        
      

     

