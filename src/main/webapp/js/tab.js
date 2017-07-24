/**
 * Created by Administrator on 2016/9/27 0027.
 */
$(function(){
        $(".tab li").mouseenter(function(){
            var $this = $(this);
            var index = $this.index();
            console.log(index);
            $(".main").eq(index).addClass("show").siblings("div").removeClass("show");


        });
});