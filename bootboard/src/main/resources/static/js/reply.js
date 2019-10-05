/**
 * Created by ranian129@gmail.com on 2019-10-04
 * Blog : http://76jin.tistory.com
 * Github : http://github.com/76jin
 *
 * * 기본 동작: 파라미터를 전달받고, Ajax로 서버 호출하여 처리.
 */
var replyManager = (function () {

  var getAll = function (obj, callback) {
    console.log("get All......");
    $.getJSON('/replies/' + obj, callback);
  };

  var add = function (obj, callback) {
    console.log("add......");

    $.ajax({
      type: 'post',
      url: '/replies/' + obj.bno,
      data: JSON.stringify(obj),
      dataType: 'json',
      contentType: "application/json",
      success: callback
    });
  };

  var update = function (obj, callback) {
    console.log("update......");
  };

  var remove = function (obj, callbcak) {
    console.log("remove......");

    $.ajax({
      type: 'delete',
      url: '/replies/' + obj.bno + '/' + obj.rno,
      dataType: 'json',
      contentType: 'application/json',
      success: callbcak
    });
  };

  return {
    getAll : getAll,
    add : add,
    update : update,
    remove : remove
  }
})();
