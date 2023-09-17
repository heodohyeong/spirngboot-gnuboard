var isMobile = false;
var ui = {}
ui.layout = {
    gnb : function(){
        var $gnb = $('#gnb > .gnb-menu > li > a');  
        var $header = $('.header');
        //var $headerWrap = $('.header');            
        if($(window).width() > 1279){            
            $gnb.on('mouseenter focusin',function(){        
                $header.addClass('active');
                var heightArr = [];
                $('.gnb-menu > li > .gnb-depth02-wrap').each(function(){
                    heightArr.push($(this).height());                 
                });          
                //console.log(heightArr); 
                var max = Math.max.apply(null, heightArr);           
                //$header.css({'height':max+90});
                $('.gnb-menu > li > .gnb-depth02-wrap').css({'height':max});            
                $(this).parent('li').addClass('active').siblings('li').removeClass('active');
                $('.layer-mask').show();               
                return false;                        
            });

            $header.on('mouseleave',function(){                   
                $gnb.removeClass('active');
                $header.css({'height':''});
                $header.removeClass('active');
                $('.gnb-menu > li').removeClass('active');
                $('.layer-mask').hide();
            });            

            //키보드 접근성
            $('.gnb-menu > li > ul > li:last-child a').on('focusout',function(){  
                $gnb.removeClass('active');
                $header.removeClass('active');
                $('.layer-mask').hide();          
            })
        } else {            
            $('#gnb > .gnb-menu > li a').on('click',function(){
                $(this).parent().addClass('active').siblings('li').removeClass('active');            
                if($(this).parent().find('ul').length > 0){                
                    return false;
                } else {
                    return true;
                }; 
            }); 
        }
        
    },
    lnb : function(){
        var $this = $('#navAside li a');      
        $this.on('click',function(){
            if($(this).parent().find('ul').length > 0){
                if($(this).parent().hasClass('active')){
                    $(this).parent().removeClass('active')
                    $(this).attr('title','하위메뉴 열림');                   
                } else {
                    $(this).parent().addClass('active').siblings('li').removeClass('active');
                    $(this).attr('title','하위메뉴 닫힘');
                }
                return false;
            } else {
                return true;
            }
        })        
    },
    searchOpen : function(){
        $('.mobile-search-btn a').on('click',function(){                
            if($(this).parent().hasClass('active')){
                $(this).parent().removeClass('active');
                $('.search-wrap').removeClass('active'); 
                $('.layer-mask').removeClass('active');
                $('html').css({'overflow':'visible'});
            } else {
                $(this).parent().addClass('active');
                $('.search-wrap').addClass('active');
                $('.layer-mask').addClass('active');
                $('html').css({'overflow':'hidden'});
            }
            return false;
        })
    }
}

$(window).load(function(){  
    ui.layout.gnb();
    ui.layout.lnb();
});

$(window).bind('resize load',function(){
    var dw = $(window).width();
    if(dw > 1024){
        $('html').removeClass('is-mobile');
        $('html').addClass('is-desktop');
    } else {
        $('html').removeClass('is-desktop');
        $('html').addClass('is-mobile');
    }
});

$(function(){   
    
    //skipnav
    $('#skipnav a').click(function() {
        var skipTo="#"+this.href.split('#')[1];
        $(skipTo).attr('tabindex', -1).on('blur focusout', function() {
            $(this).removeAttr('tabindex');
        }).focus();
        return false;
    });
    

    $('.header-foreign-wrap .btn-foreign').click(function(){  
        $(this).toggleClass('active');   
        $(this).parent().toggleClass('active');
        return false;
    });
    $('.header-foreign-wrap .header-foreign-list a').click(function(){      
        var activeText = $(this).find('span').text();
        console.log(activeText);
        $('.btn-foreign').removeClass('active');
        $('.header-foreign-wrap').removeClass('active');
        $('.btn-foreign').find('span').text(activeText);            
        return false;
    });

    //mobile menu
    $('.btn-gnb-menuall').on('click',function(){ 
        if($(this).hasClass('active')){                                   
            $(this).removeClass('active');    
            $('html').removeClass('active');
            $('.header').removeClass('active');               
            $('.header-body').removeClass('active');   
            $('.gnb-menu > li').removeClass('active');   
        } else {                    
            $(this).addClass('active');
            $('html').addClass('active');
            $('.header').addClass('active');   
            $('.header-body').addClass('active');
            $('#gnb > .gnb-menu > li:first-child').addClass('active');
        }  
        return false;   
    })
    
})
