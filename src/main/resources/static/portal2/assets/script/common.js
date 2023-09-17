//tab + layer plugin
$.fn.tabEvent = function(event) {
    this.on('click', function() { 
        if(!$(this).parent().hasClass('active')){
            $(this).parent().addClass('active').siblings('li').removeClass('active');
        } 
		return false;
    });
};
//tab + layer
$('.notice-wrap > li > a').tabEvent();
$('.news-thumb-wrap ul > li > a').tabEvent(); 

//팝업 띄우기 퍼블 사용 코드 -> jsp 화면별 정의 후 삭제 예정
$('a[href^="#popup"]').click(function(e){
    var selectedPopup = $(this).attr('href');
    $(selectedPopup).appendTo('body');
    $('.layer-mask').show();
    $('html,body').addClass('active');
    console.log(selectedPopup);
    console.log('팝업링크 a 클릭');
    $(selectedPopup).show();
    popupCenter(); //팝업중앙정렬
    //e.preventDefault();
    return false;
});

//팝업 닫기 -> 화면별 중복 정의 있어 삭제 예정
$('.popup-wrap .btn-close-popup').click(function(e){
    $('.popup-wrap, .layer-mask').hide();
    $('html, body').removeClass('active');
    return false;
}); 

//팝업레이어 중앙정렬
function popupCenter(){  
    if($('.popup-wrap').is(':visible')){
        var popupTop_ = ($(window).height()-$('.popup-wrap').outerHeight())/2 + $(window).scrollTop();
        var popupTop = Math.max(0,popupTop_);
        var popupLeft = Math.max(0,($(window).width()-$('.popup-wrap').outerWidth())/2);		       
        $('.popup-wrap').css({
            "top" : popupTop,
            "left" : popupLeft,
            "transform" : "translate(0)"
        });		       
    }        
}  








/** 기본검색 */
function defaultSearch(frm){
	if(frm.srchText.value.trim() == ""){
		alert("검색어를 입력해주세요.");
		return;
	}
	
	
	frm.submit();	
}

/** 기본검색 */
