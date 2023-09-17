
 
$(function(){   
    //$(window).load(function(){  
        //visualSlide
        var visualSlide = new Swiper('.swiper-container.visual-slide-wrap', {   
            autoplay: {
                delay: 4000,
                disableOnInteraction: false,
            },
            speed: 1500,
            slidesPerView: 'auto',
            spaceBetween: 0,
            slidesPerGroup: 1,   
            loop: true,
            pagination: {
                el: '.visual-slide-wrap .slide-control .count',
                type: "fraction",
                formatFractionCurrent: function (number) {
                    return ('0' + number).slice(-2);
                },
                formatFractionTotal: function (number) {
                    return ('0' + number).slice(-2);
                },
                renderFraction: function (currentClass, totalClass) {
                    return '<span class="' + currentClass + '"></span>' +
                        ' / ' +
                        '<span class="' + totalClass + '"></span>';
                }
            },                          
            on: {
                init: function () {
                    $('.visual-slide-wrap .swiper-slide').attr('aria-hidden','true');
                    $('.visual-slide-wrap .swiper-slide.swiper-slide-active').attr('aria-hidden','false');
                    $('.visual-slide-wrap .swiper-slide.swiper-slide-active').find("a").attr("tabIndex","0");
                },
                slideChange: function() {    
                    var index = this.activeIndex;
                    $('.visual-slide-wrap .swiper-slide').attr('aria-hidden','true');
                    $('.visual-slide-wrap .swiper-slide:eq('+index+')').attr('aria-hidden','false');
                    $('.visual-slide-wrap .swiper-slide').find("a").attr("tabIndex","-1");
                    $('.visual-slide-wrap .swiper-slide:eq('+index+')').find("a").attr("tabIndex","0");
                }
            },
            navigation: {
                nextEl: '.visual-slide-wrap .slide-control .next',
                prevEl: '.visual-slide-wrap .slide-control .prev'
            }        
        });

        //alarmSlide
        var alarmSlide = new Swiper('.swiper-container.alarm-slide-wrap', {   
            autoplay: {
                delay: 4000,
                disableOnInteraction: false,
            },
            speed: 1500,
            slidesPerView: 'auto',
            spaceBetween: 0,
            slidesPerGroup: 1,   
            loop: true,
            pagination: {
                el: '.alarm-slide-wrap .slide-control .count',
                type: "fraction",
                formatFractionCurrent: function (number) {
                    return ('0' + number).slice(-2);
                },
                formatFractionTotal: function (number) {
                    return ('0' + number).slice(-2);
                },
                renderFraction: function (currentClass, totalClass) {
                    return '<span class="' + currentClass + '"></span>' +
                           ' / ' +
                           '<span class="' + totalClass + '"></span>';
                }
            },                          
            on: {
                init: function () {
                    $('.alarm-slide-wrap .swiper-slide').attr('aria-hidden','true');
                    $('.alarm-slide-wrap .swiper-slide.swiper-slide-active').attr('aria-hidden','false');
                    $('.alarm-slide-wrap .swiper-slide.swiper-slide-active').find("a").attr("tabIndex","0");
                },
                slideChange: function() {    
                    var index = this.activeIndex;
                    $('.alarm-slide-wrap .swiper-slide').attr('aria-hidden','true');
                    $('.alarm-slide-wrap .swiper-slide:eq('+index+')').attr('aria-hidden','false');
                    $('.alarm-slide-wrap .swiper-slide').find("a").attr("tabIndex","-1");
                    $('.alarm-slide-wrap .swiper-slide:eq('+index+')').find("a").attr("tabIndex","0");
                }
            },
            navigation: {
                nextEl: '.alarm-slide-wrap .slide-control .next',
                prevEl: '.alarm-slide-wrap .slide-control .prev'
            }        
        });  

        //serviceSlide
        var serviceSlide = new Swiper('.swiper-container.service-slide-wrap', {   
            autoplay: {
                delay: 4000,
                disableOnInteraction: false,
            },
            speed: 1500,
            slidesPerView: 'auto',
            spaceBetween: 0,
            slidesPerGroup: 1,   
            loop: true,       
            breakpoints: {
                0: {
                    slidesPerView: 1,  //브라우저가 768보다 클 때
                },
                480: {
                    slidesPerView: 2,  //브라우저가 768보다 클 때
                  },
                768: {
                  slidesPerView: 3,  //브라우저가 768보다 클 때
                },
                1024: {
                  slidesPerView: 4,  //브라우저가 1024보다 클 때                  
                },
                1280: {
                    slidesPerView: 6,  //브라우저가 1024보다 클 때
                },
            },    
            on: {
                init: function () {
                    $('.service-slide-wrap .swiper-slide').attr('aria-hidden','true');
                    $('.service-slide-wrap .swiper-slide.swiper-slide-active').attr('aria-hidden','false');
                    $('.service-slide-wrap .swiper-slide.swiper-slide-active').find("a").attr("tabIndex","0");
                },
                slideChange: function() {    
                    var index = this.activeIndex;
                    $('.service-slide-wrap .swiper-slide').attr('aria-hidden','true');
                    $('.service-slide-wrap .swiper-slide:eq('+index+')').attr('aria-hidden','false');
                    $('.service-slide-wrap .swiper-slide').find("a").attr("tabIndex","-1");
                    $('.service-slide-wrap .swiper-slide:eq('+index+')').find("a").attr("tabIndex","0");
                }
            },
            navigation: {
                nextEl: '.service-slide-wrap .slide-control .next',
                prevEl: '.service-slide-wrap .slide-control .prev'
            }        
        });

        $('.visual-slide-wrap .slide-control .stop').on('click',function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
                visualSlide.autoplay.start();
                $(this).find('span').text('비주얼슬라이드 정지 버튼')
            } else {
                $(this).addClass('active')
                visualSlide.autoplay.stop();
                $(this).find('span').text('비주얼슬라이드 시작 버튼')
            }       
            return false;
        });
        $('.alarm-slide-wrap .slide-control .stop').on('click',function(){
            if($(this).hasClass('active')){
                $(this).removeClass('active');
                alarmSlide.autoplay.start();
                $(this).find('span').text('알림존 정지 버튼')
            } else {
                $(this).addClass('active')
                alarmSlide.autoplay.stop();
                $(this).find('span').text('알림존 시작 버튼')
            }       
            return false;
        });

        $('.tab-nav-content .tab-nav > ul > li').click(function(){  
            var activeIndex = $(this).index();
            $(this).addClass('active').siblings().removeClass('active');
            $(this).closest('.tab-nav-content').find('.tab-content').eq(activeIndex).addClass('active').siblings().removeClass('active');
            return false;
        });
    //}); 
    
});  
    