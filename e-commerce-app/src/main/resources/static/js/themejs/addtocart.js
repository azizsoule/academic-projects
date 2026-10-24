/* -------------------------------------------------------------------------------- /
	
	Magentech jQuery
	Created by Magentech
	v1.0 - 20.9.2016
	All rights reserved.
	
/ -------------------------------------------------------------------------------- */

	// Cart add remove functions
	var cart = {
		'add': function(product_id, customer_id, quantity, product_image, product_name) {
			$.ajax({
				type: "POST",
				url: "/api/cart/add",
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({
					"quantity" : quantity,
					"customer" : {
						"id": customer_id
					},
					"article" : {
						"idArticle" : product_id
					}
				}),
				success: function (data) {
					addProductNotice('Product added to Cart', '<img src="'+ product_image +'">', '<h3><a>'+ product_name +'</a> added to <a>shopping cart</a> !</h3>', 'success');
				},
			})
		},
		'remove': function (item_id, product_image, product_name) {
			$.ajax({
				type: "GET",
				url: "/api/cart/remove/"+item_id,
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				success: function (data) {
					addProductNotice('Product removed from Cart', '<img src="'+ product_image +'">', '<h3><a>'+ product_name +'</a> removed from your <a>shopping cart</a> !</h3>', 'success');
				},
			})
		}
	}

	var wishlist = {
		'add': function(product_id, customer_id, product_image, product_name) {
			$.ajax({
				type: "POST",
				url: "/api/wishlist/add",
				headers: {
					'Accept': 'application/json',
					'Content-Type': 'application/json'
				},
				data: JSON.stringify({
					"customer" : {
						"id": customer_id
					},
					"article" : {
						"idArticle" : product_id
					}
				}),
				success: function (data) {
					addProductNotice('Product added to Wishlist', '<img src="'+ product_image +'">', '<h3><a>'+ product_name +'</a> added to <a>your wishlist</a> !</h3>', 'success');
				},
			})
		}
	}

	var compare = {
		'add': function(product_id) {
			addProductNotice('Product added to compare', '<img src="../image/demo/shop/product/e11.jpg" alt="">', '<h3>Success: You have added <a href="#">Apple Cinema 30"</a> to your <a href="#">product comparison</a>!</h3>', 'success');
		}
	}

	/* ---------------------------------------------------
		jGrowl – jQuery alerts and message box
	-------------------------------------------------- */
	function addProductNotice(title, thumb, text, type) {
		$.jGrowl.defaults.closer = false;
		//Stop jGrowl
		//$.jGrowl.defaults.sticky = true;
		var tpl = thumb + '<h3>'+text+'</h3>';
		$.jGrowl(tpl, {		
			life: 4000,
			header: title,
			speed: 'slow',
			theme: type
		});
	}

