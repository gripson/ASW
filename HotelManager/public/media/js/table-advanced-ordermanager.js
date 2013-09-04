var TableAdvanced = function () {

    var initTable1 = function() {

        /* Formating function for row details */
        function fnFormatDetails ( oTable, nTr )
        {
            var aData = oTable.fnGetData( nTr );
            var sOut = '<table>';
           // sOut += '<tr><td>预定时间:</td><td>'+aData[2]+'</td></tr>';
		    sOut += '<tr><td>预定时间:</td><td>'+aData[7]+'</td></tr>';
			sOut += '<tr><td>入住时间:</td><td>'+aData[8]+'</td></tr>';
			sOut += '<tr><td>离店时间:</td><td>'+aData[9]+'</td></tr>';
            sOut += '<tr><td>房间保留时间:</td><td>'+aData[10]+'</td></tr>';
            sOut += '<tr><td>联系方式:</td><td>'+aData[11]+'</td></tr>';
            sOut += '<tr><td>备注:</td><td>'+aData[12]+'</td></tr>';
            sOut += '</table>';
             
            return sOut;
        }

        /*
         * Insert a 'details' column to the table
         */
        var nCloneTh = document.createElement( 'th' );
        var nCloneTd = document.createElement( 'td' );
        nCloneTd.innerHTML = '<span class="row-details row-details-close"></span>';
         
        $('#sample_1 thead tr').each( function () {
            this.insertBefore( nCloneTh, this.childNodes[0] );
        } );
         
        $('#sample_1 tbody tr').each( function () {
            this.insertBefore(  nCloneTd.cloneNode( true ), this.childNodes[0] );
        } );
         
        /*
         * Initialse DataTables, with no sorting on the 'details' column
         */
        var oTable = $('#sample_1').dataTable( {
            "aoColumnDefs": [
                {"bSortable": false, "aTargets": [ 0 ] }
            ],
            "aaSorting": [[1, 'asc']],
             "aLengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 10,
        });

        jQuery('#sample_1_wrapper .dataTables_filter input').addClass("m-wrap small"); // modify table search input
        jQuery('#sample_1_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
        jQuery('#sample_1_wrapper .dataTables_length select').select2(); // initialzie select2 dropdown
         
        /* Add event listener for opening and closing details
         * Note that the indicator for showing which row is open is not controlled by DataTables,
         * rather it is done here
         */
		$('#sample_1 a.in').live('click', function (e) {
                e.preventDefault();

                if (confirm("确定已入住了吗 ?") == false) {
                    return;
                }
				
				var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
				
                //alert(this.id);
                $.ajax({
                    type:"post",
                    url:"/order/savein",//传入action来处理
                    data: "orderdtid="+this.id+"&ttt="+Math.random(),
                    dataType: "text",
                    error:function(){
                        alert("服务器繁忙，请稍候在试！");
                    },
                    success:function(msg){
                        if(msg != null){			    
                            alert(msg);			
                            location.reload();							
                        }

                    }
                })
                oTable.fnDeleteRow(nRow);
        });
			
	    $('#sample_1 a.out').live('click', function (e) {
                e.preventDefault();

                if (confirm("确定未入住吗 ?") == false) {
                    return;
                }
				
				var nRow = $(this).parents('tr')[0];
                var aData = oTable.fnGetData(nRow);
				
                //alert(this.id);
                $.ajax({
                    type:"post",
                    url:"/order/saveout",//传入action来处理
                    data: "orderdtid="+this.id+"&ttt="+Math.random(),
                    dataType: "text",
                    error:function(){
                        alert("服务器繁忙，请稍候在试！");
                    },
                    success:function(msg){
                        if(msg != null){			    
                            alert(msg);			
                            location.reload();							
                        }

                    }
                })
                oTable.fnDeleteRow(nRow);
        });
		
        $('#sample_1').on('click', ' tbody td .row-details', function () {
            var nTr = $(this).parents('tr')[0];
            if ( oTable.fnIsOpen(nTr) )
            {
                /* This row is already open - close it */
                $(this).addClass("row-details-close").removeClass("row-details-open");
                oTable.fnClose( nTr );
            }
            else
            {
                /* Open this row */                
                $(this).addClass("row-details-open").removeClass("row-details-close");
                oTable.fnOpen( nTr, fnFormatDetails(oTable, nTr), 'details' );
            }
        });
    }

     var initTable2 = function() {
        var oTable = $('#sample_2').dataTable( {           
            "aoColumnDefs": [
                { "aTargets": [ 0 ] }
            ],
            "aaSorting": [[1, 'asc']],
             "aLengthMenu": [
                [5, 15, 20, -1],
                [5, 15, 20, "All"] // change per page values here
            ],
            // set the initial value
            "iDisplayLength": 10,
        });

        jQuery('#sample_2_wrapper .dataTables_filter input').addClass("m-wrap small"); // modify table search input
        jQuery('#sample_2_wrapper .dataTables_length select').addClass("m-wrap small"); // modify table per page dropdown
        jQuery('#sample_2_wrapper .dataTables_length select').select2(); // initialzie select2 dropdown

        $('#sample_2_column_toggler input[type="checkbox"]').change(function(){
            /* Get the DataTables object again - this is not a recreation, just a get of the object */
            var iCol = parseInt($(this).attr("data-column"));
            var bVis = oTable.fnSettings().aoColumns[iCol].bVisible;
            oTable.fnSetColumnVis(iCol, (bVis ? false : true));
        });
    }

    return {

        //main function to initiate the module
        init: function () {
            
            if (!jQuery().dataTable) {
                return;
            }

            initTable1();
            initTable2();
        }

    };

}();