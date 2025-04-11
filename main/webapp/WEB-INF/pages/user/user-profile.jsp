<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Profile</title>

<jsp:include page="../head.jsp" />

<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
<link
	href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,100..900;1,100..900&display=swap"
	rel="stylesheet" />
	
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/user-profile.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/components.css" />
	
</head>
<body>

	<jsp:include page="../header.jsp" />
	
	<div class="main-wrapper">
    
        <div class="header">
          <h2>Edit User Profile</h2>
        </div>
    
        <div class="cards-container">
          
          <!-- Photo Card -->
          <!-- Photo Card -->
            <div class="photo-card">
              <div class="profile-photo">
                <label for="photo-upload" class="upload-overlay">
                	<img src="${pageContext.request.contextPath}/resources/system/images/Avatar.png"
				     alt="Profile Photo" />
                 
                  <div class="hover-layer">
                  	<img src="${pageContext.request.contextPath}/resources/system/images/profileicon.png"
				     alt="Upload Icon" />
                    
                  </div>
                </label>
                <input type="file" id="photo-upload" accept="image/*" style="display: none;">
              </div>
              <p>Allowed *.jpeg, *.jpg, *.png, *.gif<br>Max size of 3.1 MB</p>
            </div>
    
          <!-- Details Card -->
          <div class="details-card">
            <form class="details-form">
              <div class="form-group">
                <label>Name</label>
                <input type="text" value="Foo Bar">
              </div>
    
              <div class="form-group">
                <label>Email</label>
                <input type="email" value="foo@bar.com">
              </div>
    
              <div class="form-group">
                <label>Phone number</label>
                <input type="text" value="9876543210">
              </div>
    
              <div class="form-group">
                <label>Address</label>
                <input type="text" value="Kupondole">
              </div>
    
              <div class="form-group">
                <label>Country</label>
                <select>
                  <option>Nepal</option>
                  <option>Nepal</option>
                  <option>Nepal</option>
                </select>
              </div>
    
              <div class="form-group">
                <label>State/Region</label>
                <input type="text" value="Bagmati">
              </div>
    
              <div class="form-group">
                <label>City</label>
                <input type="text" value="Kathmandu">
              </div>
    
              <div class="form-group">
                <label>Zip/Code</label>
                <input type="text" value="44600">
              </div>
    
              <div class="form-group full-width">
                <label>About</label>
                <textarea>Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae...</textarea>
              </div>
            </form>
    
            <div class="update-button-wrapper">
              <button class="update-button">Update Profile</button>
            </div>
          </div>
    
        </div>
    
      </div>
  
      <script>
        const photoInput = document.getElementById('photo-upload');
        const profileImage = document.getElementById('profileImage');
      
        photoInput.addEventListener('change', function () {
          const file = this.files[0];
          if (file && file.type.startsWith('image/')) {
            const reader = new FileReader();
            reader.onload = function (e) {
              profileImage.src = e.target.result;
            };
            reader.readAsDataURL(file);
          } else {
            alert("Please select a valid image file.");
          }
        });
      </script>
      <jsp:include page="../footer.jsp" />
</body>
</html>