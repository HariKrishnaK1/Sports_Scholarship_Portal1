$ErrorActionPreference = 'Stop'

function Invoke-JsonPost {
    param(
        [string]$Url,
        [hashtable]$Body
    )
    $json = $Body | ConvertTo-Json -Depth 5
    return Invoke-RestMethod -Method Post -Uri $Url -ContentType 'application/json' -Body $json
}

Write-Host '--- APPLY ---'
$applyRes = Invoke-JsonPost -Url 'http://localhost:8080/api/applications?userId=1' -Body @{ scholarshipId = $null; documentsUrls = 'docs1.pdf,docs2.pdf' }
$applyRes | ConvertTo-Json -Depth 6

if (-not $applyRes.applicationId) {
    throw 'Apply did not return applicationId'
}
$applicationId = [int]$applyRes.applicationId
Write-Host ("applicationId=" + $applicationId)

Write-Host '--- APPROVE ---'
$approveRes = Invoke-RestMethod -Method Post -Uri ("http://localhost:8080/api/admin/applications/$applicationId/approve")
$approveRes | ConvertTo-Json -Depth 6

Write-Host '--- REJECT ---'
$rejectRes = Invoke-RestMethod -Method Post -Uri ("http://localhost:8080/api/admin/applications/$applicationId/reject?rejectionReason=Testing")
$rejectRes | ConvertTo-Json -Depth 6

Write-Host '--- STATUS ---'
$status = Invoke-RestMethod -Method Get -Uri 'http://localhost:8080/api/applications/status/1'
$status | ConvertTo-Json -Depth 6
