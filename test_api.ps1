# Test script for Sports Scholarship Portal API

Write-Host "Testing Sports Scholarship Portal API..." -ForegroundColor Green

# Test 1: Get all scholarships
Write-Host "`n1. Testing GET /api/scholarships" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/scholarships" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $data = $response.Content | ConvertFrom-Json
    Write-Host "Found $($data.count) scholarships" -ForegroundColor Green
    foreach ($scholarship in $data.scholarships) {
        Write-Host "  - $($scholarship.name)" -ForegroundColor Cyan
    }
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 2: Test user registration
Write-Host "`n2. Testing POST /api/auth/register" -ForegroundColor Yellow
$registerData = @{
    name = "Test User"
    email = "test@example.com"
    password = "test123"
    role = "APPLICANT"
} | ConvertTo-Json

try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/register" -Method POST -Body $registerData -ContentType "application/json"
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $data = $response.Content | ConvertFrom-Json
    Write-Host "Response: $($data.message)" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 3: Test user login
Write-Host "`n3. Testing POST /api/auth/login" -ForegroundColor Yellow
$loginData = @{
    email = "alex.rodriguez@email.com"
    password = "applicant123"
} | ConvertTo-Json

try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/auth/login" -Method POST -Body $loginData -ContentType "application/json"
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $data = $response.Content | ConvertFrom-Json
    Write-Host "Response: $($data.message)" -ForegroundColor Green
    if ($data.success) {
        Write-Host "User: $($data.user.name) - Role: $($data.user.role)" -ForegroundColor Cyan
    }
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

# Test 4: Get admin dashboard
Write-Host "`n4. Testing GET /api/admin/dashboard" -ForegroundColor Yellow
try {
    $response = Invoke-WebRequest -Uri "http://localhost:8080/api/admin/dashboard" -Method GET
    Write-Host "Status: $($response.StatusCode)" -ForegroundColor Green
    $data = $response.Content | ConvertFrom-Json
    Write-Host "Dashboard data retrieved successfully" -ForegroundColor Green
} catch {
    Write-Host "Error: $($_.Exception.Message)" -ForegroundColor Red
}

Write-Host "`nAPI Testing completed!" -ForegroundColor Green
