$(document).ready(function() {

    function calculatePremium() {
		
        var sumAssured = parseFloat($("#sumAssured").val());
        var policyTerm = parseInt($("#policyTerm").val());
        $("#premiumPayingTerm").val(policyTerm);
        var paymentFrequency = $("#paymentFrequency").val().toLowerCase();

        var premiumDiscountText = $("#premiumDiscount").text();
        var premiumDiscount = parseFloat(premiumDiscountText.replace(/[^0-9.]/g, ''));
        console.log("premiumDiscount:::::::" + premiumDiscount);

        var sumAssuredDiscount = sumAssured * (premiumDiscount / 100);
        $("#sumAssuredDiscount").text(sumAssuredDiscount);
        console.log("sumAssuredDiscount:::::::" + sumAssuredDiscount);

        var premiumAfterFreq = 0;
        if (paymentFrequency === "monthly") {
			premiumAfterFreq = sumAssuredDiscount / 12;
        } else {
            premiumAfterFreq = sumAssuredDiscount;
        }
        console.log("premiumAfterFreq:::::::" + premiumAfterFreq);
	
        var premium = Math.ceil(premiumAfterFreq / policyTerm);
        $("#premium").text(premium);
        console.log("premium:::::::" + premium);

        var totalPremium = Math.ceil(premium - ((premiumDiscount / 100) * premium));
        $("#totalPremium").text(totalPremium);
         $("#hiddenTotalPremium").val(totalPremium);
        console.log("totalPremium:::::::" + totalPremium);
    }

    calculatePremium();

    $('#policyTerm').change(function() {
		console.log("in policy term");
        calculatePremium();
    });

    $('#paymentFrequency').change(function() {
		console.log("in premiumfreq");
        calculatePremium();
    });

});
