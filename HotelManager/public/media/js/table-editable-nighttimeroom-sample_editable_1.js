var TableEditable = function () {

    return {

        //main function to initiate the module
        init: function () {
            function restoreRow(oTable, nRow) {
                var aData = oTable.fnGetData(nRow);
                var jqTds = $('>td', nRow);

                for (var i = 0, iLen = jqTds.length; i < iLen; i++) {
                    oTable.fnUpdate(aData[i], nRow, i, false);
                }

                oTable.fnDraw();
            }

            function stopRow(oTable, nRow, id) {
                var aData = oTable.fnGetData(nRow);
                oTable.fnUpdate("<a id="+id+" class=\"start\" href=\"javascript:;\">开售</a>", nRow, 7, false);
				oTable.fnDraw();
            }
			
			function startRow(oTable, nRow, id) {
                var aData = oTable.fnGetData(nRow);
                oTable.fnUpdate("<a id="+id+" class=\"stop\" href=\"javascript:;\">停售</a>", nRow, 7, false);
                oTable.fnDraw();
            }

            var oTable = $('#sample_editable_1').dataTable({
                "aLengthMenu": [
                    [7, 14, 21, -1],
                    [7, 14, 21, "All"] // change per page values here
                ],
                // set the initial value
                "iDisplayLength": 7,
                "sDom": "<'row-fluid'<'span6'l><'span6'f>r>t<'row-fluid'<'span6'i><'span6'p>>",
                "sPaginationType": "bootstrap",
                "oLanguage": {
                    "sLengthMenu": "_MENU_ 每页显示数量",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    }
                },
                "aoColumnDefs": [{
                        'bSortable': false,
                        'aTargets': [0]
                    }
                ]
            });

            jQuery('#sample_editable_1_wrapper .dataTables_filter input').addClass("m-wrap medium"); // modify table search input
            jQuery('#sample_editable_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
            jQuery('#sample_editable_1_wrapper .dataTables_length select').select2({
                showSearchInput : false //hide search box with special css class
            }); // initialzie select2 dropdown

            var nEditing = null;

            $('#sample_editable_1 a.delete').live('click', function (e) {
                e.preventDefault();

                if (confirm("确定要删除吗 ?") == false) {
                    return;
                }
				
				var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
				
                //alert(aData[0]);
                $.ajax({
                    type:"post",
                    url:"/room/deletedtf",//传入action来处理
                    data: "roomtype="+aData[0]+"&ttt="+Math.random(),
                    dataType: "text",
                    error:function(){
                        alert("服务器繁忙，请稍候在试！");
                    },
                    success:function(msg){

                        if(msg != null){

                            alert(msg);
                        }

                    }
                })
                oTable.fnDeleteRow(nRow);

                //alert("Deleted! Do not forget to do some ajax to sync with backend :)");
            });

            $('#sample_editable_1 a.stop').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];
                nEditing = nRow;
                if (nEditing == nRow && this.innerHTML == "停售") {
                    /* Editing this row and want to stop it */                    
					 if (confirm("确定要停售吗 ?")) {    
                        $.ajax({
							type:"post",
							url:"/roomnight/stopsell",//传入action来处理
							data: "roomid="+this.id+"&ttt="+Math.random(),
							dataType: "text",
							error:function(){
								alert("服务器繁忙，请稍候在试！");
							},
							success:function(msg){				 
								if(msg != null){
									alert(msg);
								}
							}
						});
						stopRow(oTable, nEditing,this.id);
						nEditing = null;						
					 }
                } else {
                    /* No edit in progress - let's start one */
                    alert("停售出错！");
                }
            });
			
			$('#sample_editable_1 a.start').live('click', function (e) {
                e.preventDefault();

                /* Get the row as a parent of the link that was clicked on */
                var nRow = $(this).parents('tr')[0];
                nEditing = nRow;
                if (nEditing == nRow && this.innerHTML == "开售") {
                    /* Editing this row and want to stop it */				
                    if (confirm("确定要开售吗 ?")) {  
						$.ajax({
							type:"post",
							url:"/roomnight/startsell",//传入action来处理
							data: "roomid="+this.id+"&ttt="+Math.random(),
							dataType: "text",
							error:function(){
								alert("服务器繁忙，请稍候在试！");
							},
							success:function(msg){
					 
								if(msg != null){

									alert(msg);
								}

							}
						});
						startRow(oTable, nEditing,this.id);
						nEditing = null;
					}
                } else {
                    /* No edit in progress - let's start one */
                    alert("开售出错！");
                }
            });
        }

    };

}();