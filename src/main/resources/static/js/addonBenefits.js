$(document).ready(function() {
        // Hide elements initially (if needed)
        $("#addonPremium").attr("hidden", true);
        $("#addonPremiumRs").attr("hidden", true);

        // Function to update total premium
        function updateTotalPremium() {
            var basePremiumTxt = $("#basePremium").text();
            var basePremium = parseFloat(basePremiumTxt.replace(/,/g, '')) || 0;
            var totalPremium = basePremium;

            // Calculate the total premium based on checked checkboxes
            $('input[name="addonField"]:checked').each(function() {
                totalPremium += parseFloat($(this).val());
            });

            var premiumAddon = totalPremium.toLocaleString();
            $("#totalPremium").text(premiumAddon);
             $("#finalPremium").val(premiumAddon.replace(/,/g, ''));
        }

        // Set initial total premium
        var initialPremiumTxt = $("#totalPremium").text();
        var initialPremium = parseFloat(initialPremiumTxt.replace(/,/g, '')) || 0;
        $("#basePremium").text(initialPremium);

        // Calculate initial total premium from checked checkboxes
        updateTotalPremium();

        // Update total premium on checkbox change
        $('input[name="addonField"]').change(updateTotalPremium);
    });