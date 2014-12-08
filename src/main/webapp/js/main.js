/*
 * jQuery File Upload Plugin JS Example 8.9.1
 * https://github.com/blueimp/jQuery-File-Upload
 *
 * Copyright 2010, Sebastian Tschan
 * https://blueimp.net
 *
 * Licensed under the MIT license:
 * http://www.opensource.org/licenses/MIT
 */

/* global $, window */

$(function () {
    'use strict';

    // Initialize the jQuery File Upload widget:
    $('#fileupload').fileupload({
        // Uncomment the following to send cross-domain cookies:
        //xhrFields: {withCredentials: true},
//        url: 'upload',
    });

    // Enable iframe cross-domain access via redirect option:
    $('#fileupload').fileupload(
        'option',
        'redirect',
        window.location.href.replace(
            /\/[^\/]*$/,
            '/cors/result.html?%s'
        )
    );

//    if (window.location.hostname === 'blueimp.github.io') {
        // Demo settings:
//        $('#fileupload').fileupload('option', {
//            url: '//jquery-file-upload.appspot.com/',
//            // Enable image resizing, except for Android and Opera,
//            // which actually support image resizing, but fail to
//            // send Blob objects via XHR requests:
//            disableImageResize: /Android(?!.*Chrome)|Opera/
//                .test(window.navigator.userAgent),
//            maxFileSize: 5000000,
//            acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
//        });
//        // Upload server status check for browsers with CORS support:
//        if ($.support.cors) {
//            $.ajax({
//                url: '//jquery-file-upload.appspot.com/',
//                type: 'HEAD'
//            }).fail(function () {
//                $('<div class="alert alert-danger"/>')
//                    .text('Upload server currently unavailable - ' +
//                            new Date())
//                    .appendTo('#fileupload');
//            });
//        }
//    } else {
    	
    	 $('#fileupload').fileupload('option', {
//             url: '',
             // Enable image resizing, except for Android and Opera,
             // which actually support image resizing, but fail to
             // send Blob objects via XHR requests:
//             disableImageResize: /Android(?!.*Chrome)|Opera/
//                 .test(window.navigator.userAgent),
             maxFileSize: 5000000,
             maxNumberOfFiles:6,
             acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
         });
        // Load existing files:
        $('#fileupload').addClass('fileupload-processing');
//        $.ajax({
//            // Uncomment the following to send cross-domain cookies:
//            //xhrFields: {withCredentials: true},
//            url: $('#fileupload').fileupload('option', 'url'),
//            dataType: 'json',
//            context: $('#fileupload')[0]
//        }).always(function () {
//            $(this).removeClass('fileupload-processing');
//        }).done(function (result) {
//            $(this).fileupload('option', 'done')
//                .call(this, $.Event('done'), {result: result});
//        });
//    }
//  //定位  
//    $('#pic_select').panel({
//    	 title: "图片选择",
//    	    width: 900,
//    	    height: 400,
//    	    iconCls: 'icon-ok',
//    	    closable:true
//    });
    $('#pic_select').hide();
    $.get("upload?pageNumber=1&pageSize=10", function(data){
		 var temp_json = eval('('+data+')');    
			//console.info(json);
			//console.info(json.files);
		 $('#pp').pagination('refresh',{	// change options and refresh pager bar information
				total: temp_json.count
			});
			 $.each(temp_json.files, function (index, item) {  
                //循环获取数据    
//				 var temp_img_url = item.thumbnailUrl;
				 var temp_img_content = "<img src='"+item.thumbnailUrl+"'  class='divImg1' onclick=choose_img('"+item.thumbnailUrl+"')>"
                $("#pic_content ul li:eq("+index+")").children("div").html(temp_img_content);
				//console.info(JSON.stringify(item));
//                 $("#list").html($("#list").html() + "<br>" + name + " - " + idnumber + " - " + sex + "<br/>");  
            });  
			//$("#pic_content").html("HELLO");
		});
});
