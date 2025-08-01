package com.proyecto.utils;

public class Alert {

	public static String sweetAlertInfo(String text) {
		return sweetAlert("Información", text, "info");
	}

	public static String sweetAlertSuccess(String text) {
		return sweetAlert("Exitoso", text, "success");
	}

	public static String sweetAlertError(String text) {
		return sweetAlert("Error", text, "error");
	}

	public static String sweetAlert(String title, String msg, String icon) {
		String scriptText = """
				<script>
				    Swal.fire({
				        title: '%s',
				        text: '%s',
				        icon: '%s'
				    });
				</script>
				""";

		return String.format(scriptText, title, msg, icon);
	}

	public static String sweetToast(String title, String icon, int timer) {
		String scriptText = """
				<script>
				    const Toast = Swal.mixin({
				        toast: true,
				        position: 'top-end',
				        showConfirmButton: false,
				        timer: %d,
				        timerProgressBar: true,
				        didOpen: (toast) => {
				            toast.onmouseenter = Swal.stopTimer;
				            toast.onmouseleave = Swal.resumeTimer;
				        }
				    });
				    Toast.fire({
				        icon: '%s',
				        title: '%s'
				    });
				</script>
				""";

		return String.format(scriptText, timer, icon, title);
	}

	public static String sweetImageUrl(String title, String text, String imageUrl) {
		String scriptText = """
						<script>
							Swal.fire({
								title: '%s',
								text: '%s',
								imageUrl: '%s',
								imageWidth: 400,
								imageHeight: 400,
								
							});
						</script>
				""";
		return String.format(scriptText, title, text, imageUrl);
	}
	
	public static String sweetAlertErrorHtml(String html) {
	    return sweetAlertHtml("Error", html, "error");
	}

	public static String sweetAlertHtml(String title, String html, String icon) {
		String scriptText = """
				<script>
				    Swal.fire({
				        title: '%s',
				        html: '%s',
				        icon: '%s'
				    });
				</script>
				""";

		return String.format(scriptText, title, html, icon);
	}
}
