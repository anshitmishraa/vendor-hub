document.addEventListener('DOMContentLoaded', function() {
	// Get references to the form and table
	const createVendorForm = document.getElementById('createVendorForm');
	const vendorTableBody = document.querySelector('#vendorTable tbody');

	function editVendor(id) {
		fetch(`/vendors/${id}`)
			.then(response => response.json())
			.then(data => {
				// Populate the form fields with the fetched vendor details
				document.getElementById('updateName').value = data.name;
				document.getElementById('updateBankAccountNo').value = data.bankAccountNo;
				document.getElementById('updateBankName').value = data.bankName;
				document.getElementById('updateAddressLine1').value = data.addressLine1;
				document.getElementById('updateAddressLine2').value = data.addressLine2;
				document.getElementById('updateCity').value = data.city;
				document.getElementById('updateCountry').value = data.country;
				document.getElementById('updateZipCode').value = data.zipCode;

				// Set the vendor ID in the hidden input field
				document.getElementById('vendorId').value = id;
				// Show the edit form or navigate to the edit vendor screen

				// Show the modal
				const modal = document.getElementById('editVendorModal');
				modal.style.display = 'block';
			})
			.catch(error => {
				console.log('Error fetching vendor details:', error);
			});
	}

	// Function to handle vendor deletion
	function deleteVendor(id) {
		fetch(`/vendors/${id}`, {
				method: 'DELETE'
			})
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					return response.json().then(data => {
						throw new Error(data.message);
					});
				}
			})
			.then(data => {
				showSuccess(data.message);
				fetchVendors();
			})
			.catch(error => {
				showError(error);
			});
	}

	// Add event listener to the vendor table body
	vendorTableBody.addEventListener('click', function(event) {
		const vendorId = event.target.dataset.vendorId;

		// Check if the click event occurred on an "Edit" link
		if (event.target.tagName === 'A' && event.target.classList.contains('edit-link')) {
			event.preventDefault();
			editVendor(vendorId);
		}

		if (event.target.tagName === 'A' && event.target.classList.contains('delete-link')) {
			event.preventDefault();
			deleteVendor(vendorId);
		}
	});

	// Populate vendor table with pagination
	function populateVendorTable(vendors, pageable) {
		const vendorTableBody = document.getElementById('vendorTableBody');
		vendorTableBody.innerHTML = '';

		vendors.forEach(function(vendor) {
			const row = document.createElement('tr');
			row.innerHTML = `
              <td>${vendor.name}</td>
              <td>${vendor.bankAccountNo}</td>
              <td>${vendor.bankName}</td>
              <td><button class="edit-button" onclick="editVendor(${vendor.id})">Edit</button></td>
              <td><button class="delete-button" onclick="deleteVendor(${vendor.id})">Delete</button></td>
          `;
			vendorTableBody.appendChild(row);
		});

		// Display pagination
		const paginationContainer = document.getElementById('paginationContainer');
		paginationContainer.innerHTML = '';

		if (!pageable.empty) {
			const currentPage = pageable.pageNumber;
			const totalPages = pageable.totalPages;

			const previousButton = document.createElement('button');
			previousButton.innerText = 'Previous';
			previousButton.disabled = currentPage === 0;
            previousButton.classList.add("button");
            previousButton.id = "previousButton";
			previousButton.onclick = function() {
				fetchVendors(currentPage - 1);
			};

			const nextButton = document.createElement('button');
			nextButton.innerText = 'Next';
			nextButton.disabled = currentPage === totalPages - 1;
			nextButton.classList.add("button");
			nextButton.id = "nextButton";
			nextButton.onclick = function() {
				fetchVendors(currentPage + 1);
			};

			paginationContainer.appendChild(previousButton);
			paginationContainer.appendChild(nextButton);
		}
	}

	// Function to fetch and display the vendor list
	function fetchVendors(pageNumber = 0) {
		fetch(`/vendors?page=${pageNumber}`, {
				mode: 'cors'
			})
			.then(response => response.json())
			.then(data => {
				const vendors = data.content;
				const pageable = {
					pageNumber: data.pageable.pageNumber,
					totalPages: data.totalPages,
					empty: data.empty
				};
				populateVendorTable(vendors, pageable);
			});
	}

	// Function to handle vendor creation
	createVendorForm.addEventListener('submit', function(event) {
		event.preventDefault();

		const name = document.getElementById('name').value;
		const bankAccountNo = document.getElementById('bankAccountNo').value;
		const bankName = document.getElementById('bankName').value;
		const addressLine1 = document.getElementById('addressLine1').value;
		const addressLine2 = document.getElementById('addressLine2').value;
		const city = document.getElementById('city').value;
		const country = document.getElementById('country').value;
		const zipCode = document.getElementById('zipCode').value;

		const vendor = {
			name,
			bankAccountNo,
			bankName,
			addressLine1,
			addressLine2,
			city,
			country,
			zipCode
		};

		fetch(`/vendors`, {
				method: 'POST',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(vendor)
			})
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					return response.json().then(data => {
						throw new Error(data.message);
					});
				}
			})
			.then(data => {
				showSuccess(data.message);
				fetchVendors();
			})
			.catch(error => {
				showError(error);
			});
	});

	document.getElementById('updateVendorForm').addEventListener('submit', function(event) {
		event.preventDefault(); // Prevent the default form submission

		// Get the updated values from the form fields
		const id = document.getElementById('vendorId').value;
		const name = document.getElementById('updateName').value;
		const bankAccountNo = document.getElementById('updateBankAccountNo').value;
		const bankName = document.getElementById('updateBankName').value;
		const addressLine1 = document.getElementById('updateAddressLine1').value;
		const addressLine2 = document.getElementById('updateAddressLine2').value;
		const city = document.getElementById('updateCity').value;
		const country = document.getElementById('updateCountry').value;
		const zipCode = document.getElementById('updateZipCode').value;

		// Prepare the data object with the updated values
		const updatedVendor = {
			id: id,
			name: name,
			bankAccountNo: bankAccountNo,
			bankName: bankName,
			addressLine1: addressLine1,
			addressLine2: addressLine2,
			city: city,
			country: country,
			zipCode: zipCode
		};

		// Make a PUT request to update the vendor details
		fetch(`/vendors/${id}`, {
				method: 'PUT',
				headers: {
					'Content-Type': 'application/json'
				},
				body: JSON.stringify(updatedVendor)
			})
			.then(response => {
				if (response.ok) {
					return response.json();
				} else {
					return response.json().then(data => {
						throw new Error(data.message);
					});
				}
			})
			.then(data => {
				showSuccess(data.message);

				const modal = document.getElementById('editVendorModal');
				modal.style.display = 'none';
				fetchVendors();
			})
			.catch(error => {
				showError(error);
			});
	});

	// Fetch the initial vendor list
	fetchVendors();
});

// Close the modal when the close button is clicked
document.getElementsByClassName('close')[0].addEventListener('click', function() {
	const modal = document.getElementById('editVendorModal');
	modal.style.display = 'none';
});

// Close the modal when the modal background is clicked
window.addEventListener('click', function(event) {
	const modal = document.getElementById('editVendorModal');
	if (event.target === modal) {
		modal.style.display = 'none';

		errorContainer.style.display = 'none';
	}
});

// Function to show the error message and hide it when the close button is clicked
function showError(message) {
	const errorContainer = document.getElementById('errorContainer');
	const closeButton = errorContainer.querySelector('.close-button');
	const errorMessage = errorContainer.querySelector('.error-message');

	errorMessage.textContent = message;
	errorContainer.style.display = 'block';

	closeButton.addEventListener('click', function() {
		errorContainer.style.opacity = '0';
		setTimeout(function() {
			errorContainer.style.display = 'none';
			errorContainer.style.opacity = '1';
		}, 300);
	});
}


// Function to show the success message and automatically hide it after a delay
function showSuccess(message) {
	const successContainer = document.getElementById('successContainer');
	successContainer.textContent = message;
	successContainer.style.display = 'block';

	setTimeout(function() {
		successContainer.style.opacity = '0';
		setTimeout(function() {
			successContainer.style.display = 'none';
			successContainer.style.opacity = '1';
		}, 300);
	}, 2000); // Adjust the delay (in milliseconds) as needed
}