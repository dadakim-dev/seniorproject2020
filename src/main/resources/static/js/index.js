$(function() {
    $(".configStatus").each(function(e, el) {
        if($(el).text().trim() == "configured") {
            $(el).addClass("btn-success");
            $(el).text("Configured");
        } else if($(el).text().trim() == "init") {
            $(el).addClass("btn-secondary");
            $(el).text("Init");
        } else if($(el).text().trim() == "terminating") {
            $(el).addClass("btn-dark");
            $(el).text("Terminating");
        }
    });

    $(".nsStatus").each(function(e, el) {
        if($(el).text().trim() == "READY")
            $(el).addClass("btn-success");
        else if($(el).text().trim() == "BROKEN")
            $(el).addClass("btn-danger");
        else if($(el).text().trim() == "BUILDING")
            $(el).addClass("btn-secondary");
        else if($(el).text().trim() == "TERMINATING")
            $(el).addClass("btn-dark");
    });
});