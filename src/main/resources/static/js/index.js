$(function() {
    $(".configStatus").each(function(e, el) {
        if($(el).text().trim() == "configured") {
            $(el).addClass("btn-success").text("CONFIGURED");
        } else if($(el).text().trim() == "init") {
            $(el).addClass("btn-secondary").text("INIT");
        } else if($(el).text().trim() == "terminating") {
            $(el).addClass("btn-dark").text("TERMINATING");
        }
    });

    $(".nsStatus").each(function(e, el) {
        if($(el).text().trim() == "READY")
            $(el).addClass("btn-success");
        else if($(el).text().trim() == "BROKEN")
            $(el).addClass("btn-danger");
        else if($(el).text().trim() == "NOT_INSTANTIATED")
            $(el).addClass("btn-danger").text("NOT INSTANTIATED");
        else if($(el).text().trim() == "BUILDING")
            $(el).addClass("btn-secondary");
        else if($(el).text().trim() == "TERMINATING")
            $(el).addClass("btn-dark");
    });
});