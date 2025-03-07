$(document).ready(function() {
    $('.toggleDetails').click(function(e) {
        e.preventDefault();

        var bookingId = $(this).data('id');
        var detailsCard = $('#policyDetailCard' + bookingId);

        if (detailsCard.is(':visible')) {
            detailsCard.hide();
            $(this).text('See More');
        } else {
            detailsCard.show();
            $(this).text('See Less');
        }
    });

    // Initially hide all policy detail cards
    $('.policyDetail-card').hide();
});
